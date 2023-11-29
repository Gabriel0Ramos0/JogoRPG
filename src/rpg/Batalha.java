package rpg;

import javax.swing.JOptionPane;

public class Batalha extends Eventos {

	private RPGGame game;

    public Batalha(RPGGame game, Player player, Monstro monstro) {
        super();
        this.game = game;
        executarBatalhaEstendida(player, monstro);
    }

    public void executarBatalhaEstendida(Player player, Monstro monstro) {
        boolean vitoria = false;
        int playerArmor = player.getDefense();
        int monstroArmor = monstro.getDefesa();
        
        while (monstro.getVida() > 0 && player.isAlive()) {
            player.curaAnelRegenerativo();        	
            showPlayerInfo(player);

            int battleChoice = showBattleOptions(player, monstro);

            switch (battleChoice) {
                case 0:
                    int playerAttack = player.getAttack();
                    int playerDamage = calculateDamage(playerAttack, monstro.getDefesa());
                    
                    if (monstro.getDefesa() > 0) {
                        if (playerAttack >= monstro.getDefesa()) {
                            int defenseRemaining = Math.max(0, monstro.getDefesa() - playerAttack);
                            int excessDamage = playerAttack - monstro.getDefesa();

                            monstro.setDefesa(defenseRemaining);
                            monstro.takeDamage(excessDamage);
                            
                            JOptionPane.showMessageDialog(null, player.getName() + " ataca o monstro e causa " + playerDamage +
                                    " de dano! \n(O monstro perde a defesa e " + excessDamage + " de vida.)");
                        } else {
                            JOptionPane.showMessageDialog(null, player.getName() + " ataca o monstro e causa " + playerDamage +
                                    " de dano! \n(A defesa do monstro absorve o dano!)");
                        }
                    } else {
                        monstro.takeDamage(playerDamage);
                        JOptionPane.showMessageDialog(null, player.getName() + " ataca o monstro e causa " + playerDamage +
                                " de dano! \n(O monstro perde " + playerAttack + " de vida.)");
                    }

                    if (monstro.getVida() <= 0 && player.isAlive()) {
                        JOptionPane.showMessageDialog(null, player.getName() + " derrotou o monstro e ganhou a batalha!");
                        vitoria = true;
                        player.regenerarEscudo();
                        player.gainExperience(monstro.getXP());
                        showPlayerInfo(player);
                        game.showNextChapterOptions(vitoria, player);
                        return;
                    }
                    showMonsterStatus(monstro);
                    
                    int monstroAttack = monstro.getAtaque();
                    int monstroDamage = calculateDamage(monstroAttack, player.getDefense());

                    if (player.getDefense() > 0) {
                        if (monstroAttack >= player.getDefense()) {
                            int excessDamage = monstroAttack - player.getDefense();
                            player.takeDamage(excessDamage);

                            JOptionPane.showMessageDialog(null, monstro.getNome() + " ataca você e causa " + monstroDamage +
                                    " de dano! \n(" + player.getName() + " perde a defesa e " + excessDamage + " de vida.)");
                            player.setDefense(0);
                        } else {
                        	int defesaRestante = Math.max(0, player.getDefense() - monstroAttack);
                        	player.setDefense(defesaRestante);
                            JOptionPane.showMessageDialog(null, monstro.getNome() + " ataca você e causa " + monstroDamage +
                                    " de dano! \n(A defesa de " + player.getName() + " absorve o dano!)");
                        }
                    } else {
                        player.takeDamage(monstroDamage);
                        JOptionPane.showMessageDialog(null, monstro.getNome() + " ataca você e causa " + monstroDamage +
                                " de dano! \n(" + player.getName() + " perde " + monstroAttack + " de vida.)");
                    }
                    break;

                case 1:
                    useItemDuringBattle(player);
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, "Ao ver o tamanho de" + monstro.getNome()+ ", o herói teme seu poder e foge o mais rápido possível...");
                    vitoria = false;
                    game.showNextChapterOptions(vitoria, player);
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. " + player.getName() + " hesita na batalha.");
            }
        }
    }
    
    public static int showBattleOptions(Player player, Monstro monstro) {
        String[] battleOptions = {"Atacar", "Usar item", "Fugir"};
        return JOptionPane.showOptionDialog(null, "Escolha uma ação:\n\nStatus do Monstro:\nNome: " + monstro.getNome() + "\nVida: " + monstro.getVida() + "\nAtaque: " + monstro.getAtaque() + "\nDefesa: " + monstro.getDefesa(),
                "Batalha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, battleOptions, battleOptions[0]);
    }

    private static void showPlayerInfo(Player player) {
        JOptionPane.showMessageDialog(null, "Jogador: " + player.getName() + "\nVida: " + player.getHealth() + "\nAtaque: " + player.getAttack() + "\nDefesa: " + player.getDefense());
    }

    public void showMonsterStatus(Monstro monstro) {
        JOptionPane.showMessageDialog(null, "Status do Monstro:\nNome: " + monstro.getNome() + "\nVida: " + monstro.getVida() + "\nAtaque: " + monstro.getAtaque() + "\nDefesa: " + monstro.getDefesa());
    }

    public void useItemDuringBattle(Player player) {
        StringBuilder itemOptions = new StringBuilder("Escolha um item para usar:");
        for (int i = 0; i < player.getInventory().size(); i++) {
            Item item = player.getInventory().get(i);
            if (item instanceof Consumivel) {
                itemOptions.append("\n").append(i + 1).append(". ").append(item.getName());
            }
        }
        int itemChoice = Integer.parseInt(JOptionPane.showInputDialog(null, itemOptions.toString()));
        if (itemChoice >= 1 && itemChoice <= player.getInventory().size()) {
            Item selectedItem = player.getInventory().get(itemChoice - 1);
            if (selectedItem instanceof Consumivel) {
                Consumivel consumivel = (Consumivel) selectedItem;
                int regeneracaoVida = consumivel.getRegeneracaoVida();
                player.heal(regeneracaoVida);
                JOptionPane.showMessageDialog(null, player.getName() + " usou " + consumivel.getName() + " e recuperou " + regeneracaoVida + " de vida!");
                consumivel.reduceQuantity(1);
                if (consumivel.getQuantity() == 0) {
                    player.getInventory().remove(selectedItem);
                    JOptionPane.showMessageDialog(null, consumivel.getName() + " acabou. O item foi removido do inventário.");
                }
            }
        }
    }

    public static int calculateDamage(int attack, int defense) {
        int damage = Math.max(0, attack - defense);
        return damage;
    }
}