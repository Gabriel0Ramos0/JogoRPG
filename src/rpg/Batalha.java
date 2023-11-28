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

        while (monstro.getVida() > 0 && player.isAlive()) {
            player.curaAnelRegenerativo();        	
            showPlayerInfo(player);

            int battleChoice = showBattleOptions(player, monstro);

            switch (battleChoice) {
                case 0:
                    int playerDamage = calculateDamage(player.getAttack(), monstro.getDefesa());
                    monstro.takeDamage(playerDamage);

                    if (monstro.getDefesa() > 0) {
                        monstro.setDefesa(monstro.getDefesa() - player.getAttack());
                        if (monstro.getDefesa() < 0) {
                            int defenseRemaining = Math.abs(monstro.getDefesa());
                            monstro.takeDamage(defenseRemaining);
                            monstro.setDefesa(0);
                            JOptionPane.showMessageDialog(null, player.getName() + " ataca o monstro e causa " + playerDamage + " de dano! O monstro perde " + defenseRemaining + " de defesa e " + defenseRemaining + " de vida.");
                        } else {
                            JOptionPane.showMessageDialog(null, player.getName() + " ataca o monstro e causa " + playerDamage + " de dano! O monstro perde " + player.getAttack() + " de defesa.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, player.getName() + " ataca o monstro e causa " + playerDamage + " de dano! O monstro perde " + player.getAttack() + " de vida.");
                    }

                    if (monstro.getVida() <= 0 && player.isAlive()) {
                        JOptionPane.showMessageDialog(null, player.getName() + " derrotou o monstro e ganhou a batalha!");
                        vitoria = true;
                        game.showNextChapterOptions(vitoria, player);
                        return;
                    }
                    showMonsterStatus(monstro);

                    if (playerArmor > 0) {
                        playerArmor -= monstro.getAtaque();
                        if (playerArmor < 0) {
                            int armorRemaining = Math.abs(playerArmor);
                            player.takeDamage(armorRemaining);
                            player.setDefense(0);
                            //Arrumar dano em excesso
                            JOptionPane.showMessageDialog(null, "O monstro ataca você! Você perde " + monstro.getAtaque() + " de armadura e " + armorRemaining + " de vida.");
                        } else {
                            player.setDefense(playerArmor);
                            JOptionPane.showMessageDialog(null, "O monstro ataca você! Você perde " + monstro.getAtaque() + " de armadura.");
                        }
                    } else {
                        player.takeDamage(monstro.getAtaque());
                        JOptionPane.showMessageDialog(null, "O monstro ataca você! Você perde " + monstro.getAtaque() + " de vida.");
                    }
                    break;

                case 1:
                    useItemDuringBattle(player);
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, "Ao ver o tamanho da fera, o herói teme seu poder e foge o mais rápido possível...");
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