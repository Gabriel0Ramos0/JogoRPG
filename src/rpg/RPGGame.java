package rpg;

import javax.swing.JOptionPane;

public class RPGGame {

    public static void main(String[] args) {
        new RPGGame().startGame();
    }

    public void startGame() {
        Eventos eventos = new Eventos();
        String playerName = JOptionPane.showInputDialog(null, "Bem-vindo ao Jogo RPG!\nDigite o nome do jogador:");
        Player player = new Player(playerName);
        
        String[] classOptions = {"Escudeiro", "Lanceiro", "Arqueiro", "Espadachim"};
        String chosenClass = (String) JOptionPane.showInputDialog(
                null,
                "Escolha a classe do seu personagem:",
                "Escolha de Classe",
                JOptionPane.PLAIN_MESSAGE,
                null,
                classOptions,
                classOptions[0]);
        player.chooseClass(chosenClass);

        showPlayerInfo(player);
        showInventory(player);

        JOptionPane.showMessageDialog(null, "Prólogo: Você foi teleportado a um mundo desconhecido portando somente seus bens que carregava na hora..."
                + "\nEnquanto caminhava pela floresta tentando descobrir que mundo era aquele, você encontra uma cabana. Nela havia uma silhueta de um homem."
                + "\nDevido a luz ofuscante do sol, você não consegue identificar se o velho senhor era perigoso!");

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

                        JOptionPane.showMessageDialog(null, "Há séculos atrás, este mundo era governado por uma força maligna conhecida como 'As Trevas Insondáveis'.\n"
                                        + "As Trevas Insondáveis espalharam caos e destruição por todo o reino, mergulhando-o em uma era de trevas.\n"
                                        + "Nesse período sombrio, heróis de diversas origens se uniram para enfrentar o mal.\n"
                                        + "Uma batalha épica aconteceu entre as forças do bem e as Trevas Insondáveis.\n"
                                        + "Com grande sacrifício, os heróis conseguiram selar o mal em uma dimensão paralela, trazendo paz ao reino.\n"
                                        + "O sábio, que é um dos últimos sobreviventes dessa época, revela que as Trevas Insondáveis estão tentando retornar.\n"
                                        + "O amuleto mágico que ele lhe deu é uma relíquia antiga que canaliza poderes para enfrentar essa ameaça.");

                        JOptionPane.showMessageDialog(null, "Ele pede a você para continuar a jornada, fortalecer-se e reunir aliados para evitar que as Trevas Insondáveis retornem e mergulhem o mundo novamente na escuridão.");
                        JOptionPane.showMessageDialog(null, "Com o amuleto em mãos, você agradece ao sábio e parte em sua jornada, ciente da responsabilidade que recai sobre seus ombros.");

                        Equipavel magicAmulet = new Equipavel("Amuleto Mágico", 12, 1, 1, 5, 0);
                        JOptionPane.showMessageDialog(null, "Você ganhou um novo item: " + magicAmulet.getName());
                        player.equipItem(magicAmulet);
                        JOptionPane.showMessageDialog(null, "Ao equipar o amuleto, " + player.getName() + " Absorve o poder daquela joia!"
                                + "\n\nDefesa + 5 || Ataque + 1");
                        showPlayerInfo(player);
                        player.setTempDefense(player.getTempDefense() + 5);
                        break;

                    case 1:
                        JOptionPane.showMessageDialog(null, player.getName() + " decide ignorar o sábio. Ao olhar para ele, percebe que deixou frustado!"
                                + "\nCom cara de desaprovação, diz que os boatos se espalham rápido nesta terra."
                                + "\nSem desconfiar, " + player.getName() + " continuar sua jornada.");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. Você fica indeciso.");
                }
                break;

            case 1:
                JOptionPane.showMessageDialog(null, player.getName() + " ignora o velhor senhor e continua sua jornada.");
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. " + player.getName() + " continua sua jornada.");
        }

        Eventos.coletarItensAleatoriosComHistorias(player);

        // Simulação de batalha 
        JOptionPane.showMessageDialog(null, "Depois de horas caminhando, " + player.getName() + " encontra uma estrada."
                + "\nDecidido a seguir por ela, um monstro salta em sua direção!");

        Monstro monstro = eventos.escolherMonstroAleatorioComum();
        Batalha batalha = new Batalha(this, player, monstro);
    }

    public static void showNextChapterOptions(boolean vitoria, Player player) {
        if (vitoria) {
            JOptionPane.showMessageDialog(null, "Após matar o monstro, ele se desfaz em uma poeira dourada, deixando um item para trás."
                    + "\n" + player.getName() +" coleta o estranho objeto no formato de um amuleto, mas não consegue identificar o que ele pode fazer, mas aparenta ser valioso!");

            Item amuletoDesconhecido = new Item("Amuleto Desconhecido", 54, 1, "Vendível");
            player.getInventory().add(amuletoDesconhecido);
            JOptionPane.showMessageDialog(null, "Você encontrou um novo item: " + amuletoDesconhecido.getName());

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
                Eventos.venderItens(player);
                Eventos.comprarItens(player);
                
                JOptionPane.showMessageDialog(null, "Ao finalizar as compras, " + player.getName() + " sente uma vibração estranha no ar."
                                            + "\nUma explosão ressoa à distância e uma fumaça densa começa a subir das Montanhas Sombrias.");

                String[] escolhaInvestigar = {"Investigar a explosão", "Ignorar e continuar na caverna"};
                int escolhaExplosao = JOptionPane.showOptionDialog(null, "O que você deseja fazer?", "Explosão nas Montanhas", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, escolhaInvestigar, escolhaInvestigar[0]);
                switch (escolhaExplosao) {
                    case 0:
                        // Investiga a explosão
                        JOptionPane.showMessageDialog(null, player.getName() + " decide investigar a explosão e sai da caverna."
                                                   + "\nAo olhar na direção das Montanhas Sombrias, avista fumaça negra subindo para o céu.");
                        JOptionPane.showMessageDialog(null, "Sem perder tempo, " + player.getName() + " decide seguir na direção das Montanhas Sombrias para descobrir o que causou a explosão.");
                        Historia.eventosMontanhasSombrias(player);
                        player.marcarPassagemPorParteDaHistoria("IgnoraCave");
                        break;

                    case 1:
                        // Ignora a explosão
                        JOptionPane.showMessageDialog(null, player.getName() + " opta por ignorar a explosão e continuar explorando a Caverna das Luminescências."
                                                   + "\nA figura do vendedor sorri e continua observando " + player.getName() + " com um olhar curioso.");
                        Historia.eventosCavernaDaPerdicao(player);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. " + player.getName() + " fica indeciso sobre como reagir à explosão.");
                }
                
                break;

            case 1:
                JOptionPane.showMessageDialog(null, player.getName() +" decide ignorar a caverna e continuar sua jornada.");
                Historia.eventosDesastreTerrivel(player);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. Você fica indeciso.");
        }
    }
    
    public static void fimDaHistoria(Player player) {
        JOptionPane.showMessageDialog(null, "Ao retornar das Montanhas Sombrias, o guardião saúda você com um sorriso e lhe teleporta para o reino de MELROMARC."
                + "\nO reino celebra sua bravura, e sua história ecoará pelos séculos como um testemunho da coragem que vence as trevas.");
        JOptionPane.showMessageDialog(null, "E assim, você se torna uma lenda, lembrada não apenas pelas batalhas que travou, mas pela esperança que trouxe ao reino. "
                + "\nSeu nome: " + player.getName() + ", será cantado em canções, suas aventuras compartilhadas ao redor das fogueiras, enquanto a luz da vitória "
                + "\nilumina as Montanhas Sombrias, afastando as sombras da memória.");
        JOptionPane.showMessageDialog(null, "Parabéns por completar o Jogo. Seu personagem ficou muito forte!!");
        showPlayerInfo(player);
        String EasterEgg = JOptionPane.showInputDialog(null, "Indentificou um nome?");
        if ("Naofumi".equalsIgnoreCase(EasterEgg) && "Escudeiro".equals(player.getPlayerClass())) {
        	Historia.EasterEgg(player);
        } else {
        	JOptionPane.showMessageDialog(null, "Nome ou Classe Incorreto!!");
	        int opcaoJogarNovamente = JOptionPane.showConfirmDialog(null, "Deseja jogar novamente?", "Jogar Novamente", JOptionPane.YES_NO_OPTION);
	        if (opcaoJogarNovamente == JOptionPane.YES_OPTION) {
	            new RPGGame().startGame();
	        } else {
	            JOptionPane.showMessageDialog(null, "Obrigado por jogar! Até a próxima.");
	            System.exit(0);
	        }
        }
    }
    
    private static void showPlayerInfo(Player player) {
        JOptionPane.showMessageDialog(null, "Jogador: " + player.getName() + "\nClasse: " + player.getPlayerClass()
                + "\nVida: " + player.getHealth() + "\nAtaque: " + player.getAttack() + "\nDefesa: " + player.getDefense());
    }

    private static void showInventory(Player player) {
        StringBuilder inventoryMessage = new StringBuilder("Inventário:");
        for (Item item : player.getInventory()) {
            inventoryMessage.append("\n").append(item.getName());
        }
        JOptionPane.showMessageDialog(null, inventoryMessage.toString());
    }
    
    private static void help() {
    	JOptionPane.showMessageDialog(null, "Para comprar ou vender mais de um item, basta colocar uma vírgula (,) depois de cada item escolhido!");
    }
    
}
