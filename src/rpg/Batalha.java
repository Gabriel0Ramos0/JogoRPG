package rpg;

import javax.swing.JOptionPane;

public class Batalha extends Eventos {

	private boolean tentouFugir = false;

    public Batalha(RPGGame game, Player player, Monstro monstro) {
        super();
        executarBatalhaEstendida(player, monstro);
    }
    
	public void executarBatalhaEstendida(Player player, Monstro monstro) {
        boolean vitoria = false;
        
        while (monstro.getVida() > 0 && player.isAlive()) {
            player.curaAnelRegenerativo();        	
            showPlayerInfo(player);

            int battleChoice = showBattleOptions(player, monstro);

            switch (battleChoice) {
                case 0:
                    int playerAttack = player.getAttack();
                    int playerDamage = calculateDamage(playerAttack, monstro.getDefesa());
                    
                    //Player ataca monstro
                    if (monstro.getDefesa() > 0) {
                        if (playerAttack >= monstro.getDefesa()) {
                            int excessDamage = playerAttack - monstro.getDefesa();
                            
                            JOptionPane.showMessageDialog(null, player.getName() + " ataca o " +monstro.getNome() +" e causa " + playerDamage +
                                    " de dano! \n(O " +monstro.getNome() +" perde a defesa e " + excessDamage + " de vida.)");
                            monstro.setDefesa(0);
                            monstro.setVida(monstro.getVida() - excessDamage);
                        } else {
                        	int defenseRemaining = Math.max(0, monstro.getDefesa() - playerAttack);
                        	monstro.setDefesa(defenseRemaining);
                            JOptionPane.showMessageDialog(null, player.getName() + " ataca o " +monstro.getNome() +" e causa " + playerDamage +
                                    " de dano! \n(A defesa do " +monstro.getNome() +" absorve o dano!)");
                        }
                    } else {
                        monstro.takeDamage(playerDamage);
                        JOptionPane.showMessageDialog(null, player.getName() + " ataca o " +monstro.getNome() +" e causa " + playerDamage +
                                " de dano! \n(O " +monstro.getNome() +" perde " + playerAttack + " de vida.)");
                    }

                    if (monstro.getVida() <= 0 && player.isAlive()) {
                        JOptionPane.showMessageDialog(null, player.getName() + " derrotou o " +monstro.getNome() +" e ganhou a batalha!");
                        vitoria = true;
                        player.regenerarEscudo();
                        player.resetConsumableEffects();
                        player.gainExperience(monstro.getXP());
                        player.setCoins(player.getCoins() + monstro.getOuro());
                        showPlayerInfo(player);
                        
                        if (player.hasArmourAncestral()) {
                        	player.ganhaEscudoAdicional();
                            JOptionPane.showMessageDialog(null, "A Armadura Ancestral concedeu 1 de escudo adicional!");
                        }
                        
                        //Verifica Progresso de História
                        if (!player.jaPassouPorParteDaHistoria("Prólogo")) {
                        	player.marcarPassagemPorParteDaHistoria("Prólogo");
                            RPGGame.showNextChapterOptions(vitoria, player);
                        } else if (!player.jaPassouPorParteDaHistoria("Caverna")){
                        	player.marcarPassagemPorParteDaHistoria("Caverna");
                            Historia.continuaHistoriaCave(vitoria, player);
                        } else if (!player.jaPassouPorParteDaHistoria("IgnoraCave")) {
                        	player.marcarPassagemPorParteDaHistoria("IgnoraCave");
                        	Historia.continuaHistoriaForaCave(vitoria, player);
                        } else if (!player.jaPassouPorParteDaHistoria("ForaCave")) {
                        	player.marcarPassagemPorParteDaHistoria("ForaCave");
                        	Historia.eventosDesastreTerrivel(player);
                        } else if (!player.jaPassouPorParteDaHistoria("Préluta")) {
                        	player.marcarPassagemPorParteDaHistoria("Préluta");
                        	Historia.destinoFinal(player);
                        } else if (!player.jaPassouPorParteDaHistoria("Final")) {
                        	player.marcarPassagemPorParteDaHistoria("Final");
                        	Historia.finalBatalhaFinal(vitoria, player);
                        }
                        return;
                    } else if (player.getHealth() <=0){
            	    	JOptionPane.showMessageDialog(null, player.getName() + " é derrotado pelo poder avassalador do " + monstro.getNome() +"."
            	    			+ "\nA escuridão consome você enquanto desmaia na trilha.");
            	    	JOptionPane.showMessageDialog(null, "---GAME OVER---");
            	    	Historia.jogarNovamente();
            	   }                
                    showMonsterStatus(monstro);
                    
                    //Monstro ataca player
                    int danomais = player.getDanoRecebidoExtra();
                    int monstroAttack = monstro.getAtaque() + danomais;
                    int monstroDamage = calculateDamage(monstroAttack, player.getDefense());

                    if (player.getDefense() > 0) {
                        if (monstroAttack >= player.getDefense()) {
                            int excessDamage = monstroAttack - player.getDefense();
                            
                            JOptionPane.showMessageDialog(null, monstro.getNome() + " ataca você e causa " + monstroDamage +
                                    " de dano! \n(" + player.getName() + " perde a defesa e " + excessDamage + " de vida.)");
                            player.setDefense(0);
                            player.setHealth(player.getHealth() - excessDamage);
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
                    useItemDuringBattle(player, monstro);
                    break;

                case 2:
                	if (!tentouFugir) {
                        JOptionPane.showMessageDialog(null, "Ao ver o tamanho de " + monstro.getNome() + ", o herói teme seu poder e tenta fugir o mais rápido possível...");
                        setTentouFugir(true);
                        vitoria = false;
                        player.resetConsumableEffects();
                        if (!player.jaPassouPorParteDaHistoria("Prólogo")) {
                        	player.marcarPassagemPorParteDaHistoria("Prólogo");
                            RPGGame.showNextChapterOptions(vitoria, player);
                        } else {
                        	JOptionPane.showMessageDialog(null, "Você tenta fugir novamente, mas o " + monstro.getNome() + " bloqueia sua saída!");
                            executarBatalhaEstendida(player, monstro);
                        }
                        return;
                    }
                    break;

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

    public void useItemDuringBattle(Player player, Monstro monstro) {
        StringBuilder itemOptions = new StringBuilder("Escolha um item para usar:");
        for (int i = 0; i < player.getInventory().size(); i++) {
            Item item = player.getInventory().get(i);
            if (item instanceof Consumivel) {
                Consumivel consumivel = (Consumivel) item;
                itemOptions.append("\n").append(i + 1).append(". ").append(item.getName())
                        .append(" (+").append(consumivel.getRegeneracaoVida()).append(" de regeneração de vida)");
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
                
                if (selectedItem.getName().equals("Pergaminho de Teletransporte")) {
                    JOptionPane.showMessageDialog(null, player.getName() + " usa seu pergaminho caro de teleporte, um brilho envolve " + player.getName() + " e num clarão o deixa no mesmo lugar. "
                    		+ "\nAparentemente, aquele vendedor te tapeou.");
                } else if (selectedItem.getName().equals("Elixir de Resistência")) {
                    JOptionPane.showMessageDialog(null, "Ao tomar o Elixir de Resistência, pedras e pedaços de terra são atraídos a " + player.getName() + ", aumentando seu poder de defesa. "
                    		+ "\n ||+50 de armadura||");
                    player.setDefense(player.getDefense() + 50);
                } else if (selectedItem.getName().equals("Poção de Invisibilidade")) {
                    JOptionPane.showMessageDialog(null, "Ao beber a Poção de Invisibilidade, " + player.getName() + " não consegue mais se ver, mas o monstro ainda consegue. "
                    		+ "\nParece que " + player.getName() + " foi tapeado.");
                } else if (selectedItem.getName().equals("Elixir de Força")) {
                    if (!player.isElixirConsumido()) {
                        JOptionPane.showMessageDialog(null, "Ao beber o Elixir de Força, a força de " + player.getName() + " aumenta em 3.");
                        player.setAttack(player.getAttack() + 3);
                        player.setElixirConsumido(true);
                    } else {
                        JOptionPane.showMessageDialog(null, player.getName() + " já consumiu o Elixir de Força e não tem efeito adicional.");
                    }
                } else if (selectedItem.getName().equals("Poção de Velocidade")) {
                	int playerAttack = player.getAttack() * 2;
                    int playerDamage = calculateDamage(playerAttack, monstro.getDefesa());
                    JOptionPane.showMessageDialog(null, "Bebendo a Poção de Velocidade, " + player.getName() + " se sente muito rápido e vê tudo em câmera lenta. " 
                    		+ "\n" + player.getName() + " ganhou 2 turnos de pressa e ataca o(a) " + monstro.getNome() + ".");
                    if (monstro.getDefesa() > 0) {
                        if (playerAttack >= monstro.getDefesa()) {
                            int excessDamage = playerAttack - monstro.getDefesa();                                                       
                            JOptionPane.showMessageDialog(null, player.getName() + " ataca o " +monstro.getNome() +" e causa " + playerDamage +
                                    " de dano! \n(O " +monstro.getNome() +" perde a defesa e " + excessDamage + " de vida.)");
                            monstro.setDefesa(0);
                            monstro.setVida(monstro.getVida() - excessDamage);
                        } else {
                        	int defenseRemaining = Math.max(0, monstro.getDefesa() - playerAttack);
                        	monstro.setDefesa(defenseRemaining);
                            JOptionPane.showMessageDialog(null, player.getName() + " ataca o " +monstro.getNome() +" e causa " + playerDamage +
                                    " de dano! \n(A defesa do " +monstro.getNome() +" absorve o dano!)");
                        }
                    } else {
                        monstro.takeDamage(playerDamage);
                        JOptionPane.showMessageDialog(null, player.getName() + " ataca o " +monstro.getNome() +" e causa " + playerDamage +
                                " de dano! \n(O " +monstro.getNome() +" perde " + playerAttack + " de vida.)");
                    }
                }
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

	public boolean isTentouFugir() {
		return tentouFugir;
	}

	public void setTentouFugir(boolean tentouFugir) {
		this.tentouFugir = tentouFugir;
	}
    
}