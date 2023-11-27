package rpg;

import java.util.List;

import javax.swing.JOptionPane;

public class RPGGame {
    public static void main(String[] args) {
    	Eventos eventos = new Eventos();
        String playerName = JOptionPane.showInputDialog(null, "Bem-vindo ao Jogo RPG!\nDigite o nome do jogador:");

        Player player = new Player(playerName);
        
        Item bread = new Item("Pão", 2, 3);
        Item cellphone = new Item("Celular", 15, 1);
        Item watch = new Item("Relógio", 12, 1);

        player.getInventory().add(bread);
        player.getInventory().add(cellphone);
        player.getInventory().add(watch);

        showPlayerInfo(player);
        showInventory(player);

        JOptionPane.showMessageDialog(null, "Prólogo: Você foi teleportado a um mundo desconhecido portando somente seus bens que carregava na hora..."
                + "\nEnquanto caminhava pela floresta tentando descobrir que mundo era aquele, você encontra uma cabana. Nela havia uma silhueta de um homem."
                + "\nDevido a luz ofuscante do sol, você não consegue identificar se o velho senhor era perigoso!");

        // Situação de escolha
        String[] options = {"Conversar", "Ignorar o velho senhor e continuar a jornada"};
        int choice = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "O velho revelou ser um sábio e gostaria de te contar a história deste mundo.");
                
                String[] opções = {"Ouvir hitória", "Estou com pressa"};
                int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opções, opções[0]);

                switch (escolha) {
                case 0:
                    JOptionPane.showMessageDialog(null, player.getName() + " decide ouvir a história do mundo.");
                    JOptionPane.showMessageDialog(null, "Percebendo sua humildade, o sábio lhe entrega um amuleto e começa a contar a história do mundo.");

                    JOptionPane.showMessageDialog(null, "Há séculos atrás, este mundo era governado por uma força maligna conhecida como 'As Trevas Insondáveis'.");
                    JOptionPane.showMessageDialog(null, "As Trevas Insondáveis espalharam caos e destruição por todo o reino, mergulhando-o em uma era de trevas.");
                    JOptionPane.showMessageDialog(null, "Nesse período sombrio, heróis de diversas origens se uniram para enfrentar o mal.");
                    JOptionPane.showMessageDialog(null, "Uma batalha épica aconteceu entre as forças do bem e as Trevas Insondáveis.");
                    JOptionPane.showMessageDialog(null, "Com grande sacrifício, os heróis conseguiram selar o mal em uma dimensão paralela, trazendo paz ao reino.");
                    JOptionPane.showMessageDialog(null, "O sábio, que é um dos últimos sobreviventes dessa época, revela que as Trevas Insondáveis estão tentando retornar.");
                    JOptionPane.showMessageDialog(null, "O amuleto mágico que ele lhe deu é uma relíquia antiga que canaliza poderes para enfrentar essa ameaça.");

                    JOptionPane.showMessageDialog(null, "Ele pede a você para continuar a jornada, fortalecer-se e reunir aliados para evitar que as Trevas Insondáveis retornem e mergulhem o mundo novamente na escuridão.");
                    JOptionPane.showMessageDialog(null, "Com o amuleto em mãos, você agradece ao sábio e parte em sua jornada, ciente da responsabilidade que recai sobre seus ombros.");

                    Item magicAmulet = new Item("Amuleto Mágico", 0, 1);
                    player.getInventory().add(magicAmulet);
                    JOptionPane.showMessageDialog(null, "Você ganhou um novo item: " + magicAmulet.getName());
                    player.equipItem(magicAmulet);
                    JOptionPane.showMessageDialog(null, "Ao equipar o amuleto, "+ player.getName()+ " sente que seu poder aumentou!"
                    		                   + "\n\nDefesa + 5 || Ataque + 1");
                    player.setDefense(15);
                    player.setAttack(6);
                    break;

                case 1:
                    JOptionPane.showMessageDialog(null, player.getName() +" decide ignorar o sábio. Ao olhar para ele, percebe que deixou frustado!"
                    		+ ", com cara de desaprovação diz que os boatos se espalham rápido nesta terra."
                    		+ "\nSem desconfiar, "+ player.getName() +" continuar sua jornada.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Você fica indeciso.");
                }
                break;

            case 1:
                JOptionPane.showMessageDialog(null, player.getName() +" ignora o velhor senhor e continua sua jornada.");
                break;
                
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. "+ player.getName() +" continua sua jornada.");
        }
        eventos.coletarItensAleatoriosComHistorias(player);

     // Simulação de batalha
        List<Item> inventory = player.getInventory();
        JOptionPane.showMessageDialog(null, "Depois de horas caminhando, " + player.getName() + " encontra uma estrada."
                + "\nDecidido a seguir por ela, um monstro salta em sua direção!");

        Monstro monstro = new Monstro("Besta", 25, 5, 5);
        boolean vitoria = false;
        int playerArmor = player.getDefense();

        while (monstro.getVida() > 0 && player.isAlive()) {
            showPlayerInfo(player);
            showInventory(player);
            Item primeiroItem = inventory.get(0);
            primeiroItem.use(player);

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
                        showNextChapterOptions(vitoria, player);
                        return;
                    }
                    showMonsterStatus(monstro);

                    if (playerArmor > 0) {
                        playerArmor -= monstro.getAtaque();
                        if (playerArmor < 0) {
                            int armorRemaining = Math.abs(playerArmor);
                            player.takeDamage(armorRemaining);
                            player.setDefense(0);
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
                    showNextChapterOptions(vitoria, player);
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. " + player.getName() + " hesita na batalha.");
            }
        }
    }

    private static void showPlayerInfo(Player player) {
        JOptionPane.showMessageDialog(null, "Jogador: " + player.getName() + "\nVida: " + player.getHealth() + "\nAtaque: " + player.getAttack() + "\nDefesa: " + player.getDefense());
    }

    private static void showInventory(Player player) {
        StringBuilder inventoryMessage = new StringBuilder("Inventário:");
        for (Item item : player.getInventory()) {
            inventoryMessage.append("\n").append(item.getName());
        }
        JOptionPane.showMessageDialog(null, inventoryMessage.toString());
    }

    private static int showBattleOptions(Player player, Monstro monstro) {
        String[] battleOptions = {"Atacar", "Usar item", "Fugir"};
        return JOptionPane.showOptionDialog(null, "Escolha uma ação:\n\nStatus do Monstro:\nNome: " + monstro.getNome() + "\nVida: " + monstro.getVida() + "\nAtaque: " + monstro.getAtaque() + "\nDefesa: " + monstro.getDefesa(),
                "Batalha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, battleOptions, battleOptions[0]);
    }

    private static void showMonsterStatus(Monstro monstro) {
        JOptionPane.showMessageDialog(null, "Status do Monstro:\nNome: " + monstro.getNome() + "\nVida: " + monstro.getVida() + "\nAtaque: " + monstro.getAtaque() + "\nDefesa: " + monstro.getDefesa());
    }

    private static void useItemDuringBattle(Player player) {
        StringBuilder itemOptions = new StringBuilder("Escolha um item para usar:");
        for (int i = 0; i < player.getInventory().size(); i++) {
            itemOptions.append("\n").append(i + 1).append(". ").append(player.getInventory().get(i).getName());
        }
        int itemChoice = Integer.parseInt(JOptionPane.showInputDialog(null, itemOptions.toString()));
        if (itemChoice >= 1 && itemChoice <= player.getInventory().size()) {
            Item selectedItem = player.getInventory().get(itemChoice - 1);

            // Aplica os efeitos do item
            if (selectedItem.getName().equals("Pão")) {
                if (selectedItem.getQuantity() > 0) {
                    selectedItem.decrementQuantity();
                    int healthRegen = 10;
                    player.heal(healthRegen);
                    JOptionPane.showMessageDialog(null,  player.getName() +" come o pão e recupera " + healthRegen + " de vida!");
                    if (selectedItem.getQuantity() == 0) {
                        player.getInventory().remove(selectedItem);
                        JOptionPane.showMessageDialog(null, "Seu pão acabou! O item foi removido do inventário.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Você está sem pão! Escolha outra ação.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Escolha inválida. A batalha continua.");
            }
        }
    }

    private static void showNextChapterOptions(boolean vitoria, Player player) {
    	Eventos eventos = new Eventos();
        if (vitoria) {
            JOptionPane.showMessageDialog(null, "Após matar o monstro, ele se desfaz em uma poeira dourada, deixando um item para trás."
                    + "\n" + player.getName() +" coleta o estranho objeto, mas não consegue identificar o que é.");

            Item raroDesconhecido = new Item("Raro Desconhecido", 0, 1);
            player.getInventory().add(raroDesconhecido);
            JOptionPane.showMessageDialog(null, "Você encontrou um novo item: " + raroDesconhecido.getName());

            JOptionPane.showMessageDialog(null, "Seguindo pela estrada, "+ player.getName() +" avista uma caverna com um brilho misterioso no seu interior.");
        } else {
            JOptionPane.showMessageDialog(null, "Você corre para longe do monstro, escapando de seu alcance."
            		+ "\nEnquanto corria, você sentia que estava sendo observado."
                    + "\nApós correr por horas, "+ player.getName() +" avista uma caverna com um brilho misterioso no seu interior.");
        }

        String[] options = {"Entrar na caverna", "Ignorar a caverna e continuar a jornada"};
        int choice = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Nova Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, player.getName() +" decide entrar na caverna.");
                JOptionPane.showMessageDialog(null, "Ao se aproximar, "+ player.getName() +" descobre uma pequena clareira iluminada por cristais roxos incrustados nas paredes. "
                		+ "\nNo centro, uma figura peculiar está sentada em uma pedra, emitindo a luz hipnotizante."
                		+ "\nA figura revela-se como um vendedor peculiar, vestindo roupas exóticas que parecem refletir as cores dançantes ao redor. "
                		+ "\nSua loja improvisada exibe itens únicos, cada um banhado pela luz roxa que dá vida à caverna."
                		+ "\nSaudações, aventureiro, à Caverna das Luminescências! "
                		+ "\nAqui, os cristais guardiões iluminam os tesouros que ofereço, diz o vendedor com um sorriso acolhedor. "
                		+ "\nEle explica que esses itens têm propriedades especiais, alimentadas pela energia única da caverna.");
                JOptionPane.showMessageDialog(null, "A luz roxa cintila enquanto "+ player.getName() +" explora as opções disponíveis. Cada item, carregado com a energia mágica da caverna, promete auxiliar na jornada à frente.");
                help();
                eventos.venderItens(player);
                eventos.comprarItens(player);
                break;

            case 1:
                JOptionPane.showMessageDialog(null, player.getName() +" decide ignorar a caverna e continuar sua jornada.");
                // Implemente a lógica para a escolha de ignorar a caverna
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. Você fica indeciso.");
        }
    }
    
    private static void help() {
    	JOptionPane.showMessageDialog(null, "Para comprar ou vender mais de um item, basta colocar uma vírgula (,) depois de cada item escolhido!");
    }
    
    private static int calculateDamage(int attack, int defense) {
        int damage = Math.max(0, attack - defense);
        return damage;
    }
}
