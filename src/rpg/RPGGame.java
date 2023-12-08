package rpg;

import javax.swing.JOptionPane;

public class RPGGame {

    public static void main(String[] args) {
        new RPGGame().startGame();
    }

    public void startGame() {
        Eventos eventos = new Eventos();
        									//Mundo Perdido: Despertar das Sombras
        JOptionPane.showMessageDialog(null, "*.*.*Lost World: Awakening Shadows*.*.*"
        		+ "\n        *.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*");
        JOptionPane.showMessageDialog(null, "Bem-vindo à Terra de Melromarc, um reino cheio de magia e mistérios!"
        		+ "\nVocê, nobre aventureiro, está prestes a embarcar em uma jornada épica repleta de desafios e descobertas."
        		+ "\nAntes de entrar nessa incrível aventura, precisamos saber quem é você...");
        String playerName = JOptionPane.showInputDialog(null, "Digite o seu nome, destemido herói:");
        Player player = new Player(playerName);
        JOptionPane.showMessageDialog(null, "Bem-vindo, " + player.getName() + "! Que seus passos guiem os destinos desta história."
        		+ "\nPrepare-se para enfrentar perigos, desvendar enigmas e se tornar uma lenda em Melromarc!");
        JOptionPane.showMessageDialog(null, "Antes de começar sua jornada, é hora de decidir o caminho que moldará seu destino em Melromarc."
        		+ "\nEscolha com sabedoria a classe que melhor se alinha com sua personalidade e estilo de combate.");
        
        String[] classOptions = {"Escudeiro", "Lanceiro", "Arqueiro", "Mago Arcano", "Espadachim"};
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
        player.setCoins(150);
        H_Situacional.Sequestro(player);

        JOptionPane.showMessageDialog(null, "Prólogo: Você foi teleportado a um mundo desconhecido, sentindo-se perdido e desorientado, carregando apenas os pertences que tinha consigo..."
        	    + "\nÀ medida que seus olhos se ajustam à nova paisagem, você se encontra em uma vasta e misteriosa floresta, onde árvores altas e exóticas se entrelaçam formando uma cúpula verdejante.");
        	        
        JOptionPane.showMessageDialog(null, "Enquanto explora a natureza desconhecida à sua volta, seu caminho o leva a uma clareira onde uma cabana solitária se destaca contra a paisagem."
        	    + "\nA madeira envelhecida da cabana parece conter histórias antigas, e a porta entreaberta convida à curiosidade."
        	    + "\nAo se aproximar, uma silhueta enrugada de um homem idoso se desenha contra a penumbra do interior da cabana."
        	    + "\nA luz intensa do sol obscurece sua visão, criando um jogo de sombras que impede você de discernir se o velho senhor representa perigo ou sabedoria.");

        String[] options = {"Conversar", "Ignorar o velho senhor e continuar a jornada"};
        int choice = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
        case 0:
            JOptionPane.showMessageDialog(null, "O velho revela-se como um sábio de aparência enigmática. Seus olhos, profundos e repletos de sabedoria, parecem esconder segredos milenares."
                    + "\nCom uma voz grave, ele convida você a entrar em sua humilde cabana, dizendo: 'Vejo em seus olhos a sede por conhecimento. "
                    + "\nPor favor, entre e compartilharei a história deste mundo com você.'");
            JOptionPane.showMessageDialog(null, "Curioso e intrigado, " + player.getName() + " aceita o convite do sábio e adentra a cabana."
                    + "\nÀ medida que a porta se fecha, uma aura mágica parece envolver o local, aumentando ainda mais o mistério.");

            String[] opções = {"Perguntar sobre a história", "Expressar gratidão e sair"};
                int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opções, opções[0]);

                switch (escolha) {
                    case 0:
                        JOptionPane.showMessageDialog(null, player.getName() + " decide ouvir a história do mundo.");
                        JOptionPane.showMessageDialog(null, "Percebendo sua humildade, o sábio lhe entrega um amuleto e começa a contar a história do mundo.");

                        JOptionPane.showMessageDialog(null, "Há séculos atrás, este mundo era governado por uma força maligna conhecida como 'As Trevas Insondáveis'."
                                        + "\nAs Trevas Insondáveis espalharam caos e destruição por todo o reino, mergulhando-o em uma era de trevas."
                                        + "\nNesse período sombrio, heróis de diversas origens se uniram para enfrentar o mal."
                                        + "\nUma batalha épica aconteceu entre as forças do bem e as Trevas Insondáveis."
                                        + "\nCom grande sacrifício, os heróis conseguiram selar o mal em uma dimensão paralela, trazendo paz ao reino."
                                        + "\nO sábio, que é um dos últimos sobreviventes dessa época, revela que as Trevas Insondáveis estão tentando retornar."
                                        + "\nO amuleto mágico que ele lhe deu é uma relíquia antiga que canaliza poderes para enfrentar essa ameaça.");

                        JOptionPane.showMessageDialog(null, "Ele pede a você para continuar a jornada, fortalecer-se e reunir aliados para evitar que as Trevas Insondáveis retornem e mergulhem o mundo novamente na escuridão.");
                        JOptionPane.showMessageDialog(null, "Com o amuleto em mãos, você agradece ao sábio e parte em sua jornada, ciente da responsabilidade que recai sobre seus ombros.");

                        Equipavel magicAmulet = new Equipavel("Amuleto Mágico", 12, 1, 1, 5, 0);
                        JOptionPane.showMessageDialog(null, "Você ganhou um novo item: " + magicAmulet.getName());
                        player.equipItem(magicAmulet);
                        if ("Escudeiro".equals(player.getPlayerClass())) {
                        	JOptionPane.showMessageDialog(null, "Ao equipar o amuleto, " + player.getName() + " Absorve o poder daquela joia!"
                        			+ "\nSeu escudo reage ao poder da joia e aprimora ainda mais seus efeitos!"
                        			+ "\n\nDefesa de 5 para + 7 || Ataque de 1 para + 2");
                        	player.setAttack(player.getAttack() + 1);
                        	player.setDefense(player.getDefense() + 2);
                        	player.setTempDefense(player.getTempDefense() + 2);
                        } else {
                        	JOptionPane.showMessageDialog(null, "Ao equipar o amuleto, " + player.getName() + " Absorve o poder daquela joia!"
                                    + "\n\nDefesa + 5 || Ataque + 1");
                        }
                        showPlayerInfo(player);
                        player.setTempDefense(player.getTempDefense() + 5);
                        break;

                    case 1:
                    	player.marcarPassagemPorParteDaHistoria("NãoOuvirHistoria");
                        JOptionPane.showMessageDialog(null, player.getName() + " decide não ouvir a história do sábio. Ao fixar seus olhos nele, nota a expressão de desapontamento em seu rosto enrugado."
                                + "\nO sábio, com um suspiro, diz com uma voz serena, 'Cada escolha molda nosso destino, e o caminho que você escolheu pode ter consequências imprevisíveis.'"
                                + "\nApesar da sensação incômoda, " + player.getName() + " continua sua jornada, sentindo o peso das palavras do sábio ecoando em sua mente.");
                        JOptionPane.showMessageDialog(null, "À medida que você se afasta da cabana, uma sensação estranha paira no ar, como se os sussurros das árvores comentassem sua decisão."
                                + "\nAs sombras ao seu redor parecem mais densas, e o vento carrega ecos misteriosos. "
                                + "\nSerá que a escolha de não ouvir a história terá impactos mais profundos do que " + player.getName() + " imagina?");
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
        JOptionPane.showMessageDialog(null, "Enquanto você atravessa densas florestas, os sons da natureza criam uma melodia serena."
            + "\nSubitamente, um arrepio percorre sua espinha, e você percebe que não está sozinho. "
            + "\nA atmosfera ao redor parece se tornar densa, e sombras começam a dançar entre as árvores."
            + "\nDe repente, um rugido ecoa, e um par de olhos amarelos brilha na escuridão. Um monstro surge das sombras!");

        Monstro monstro = eventos.escolherMonstroAleatorioComum();

        JOptionPane.showMessageDialog(null, "Você se depara com um " + monstro.getNome() + "! Ele emerge com olhos faiscantes e presas afiadas, pronto para atacar.");
        JOptionPane.showMessageDialog(null, "Prepare-se para a batalha!");

        Batalha batalha = new Batalha(this, player, monstro);

    }

    public static void showNextChapterOptions(boolean vitoria, Player player) {
    	if (vitoria) {
    		if ("Arqueiro".equals(player.getPlayerClass())) {
    			JOptionPane.showMessageDialog(null, "Com um golpe final, você derrota o monstro, e ele se desfaz em uma poeira dourada, deixando um item para trás."
                        + "\n" + player.getName() + " coleta o estranho objeto em formato de amuleto, incapaz de identificar seu propósito, mas ele parece valioso!");
    			JOptionPane.showMessageDialog(null, "Derrepente seu arco brilha, e a ponta da flexa absorve o poder da joia, desvendando o seu significado."
    					+ "\nO medalhão revela inscrições mencionando a família real de Melromarc. O medalhão é muito valioso!!");
                Item medalhão = new Item("Medalhão de Melromarc", 100, 1, "Vendível");
                player.getInventory().add(medalhão);
                JOptionPane.showMessageDialog(null, "Você encontrou um novo item: " + medalhão.getName());
            } else {
            	JOptionPane.showMessageDialog(null, "Com um golpe final, você derrota o monstro, e ele se desfaz em uma poeira dourada, deixando um item para trás."
                        + "\n" + player.getName() + " coleta o estranho objeto em formato de amuleto, incapaz de identificar seu propósito, mas ele parece valioso!");
                Item amuletoDesconhecido = new Item("Amuleto Desconhecido", 54, 1, "Vendível");
                player.getInventory().add(amuletoDesconhecido);
                JOptionPane.showMessageDialog(null, "Você encontrou um novo item: " + amuletoDesconhecido.getName());
            }
            JOptionPane.showMessageDialog(null, "Enquanto você examina melhor o amuleto, percebe que ele emite um leve brilho. Curioso(a) com o que isso pode significar, você decide explorar mais a floresta."
            		+ "\nCaminhando pela densa floresta, você avista uma trilha que se desvia do caminho principal. "
            		+ "\nDecidindo seguir a trilha, você se encontra imerso na natureza, ouvindo os sons dos pássaros e sentindo o cheiro das árvores."
            		+ "\nA trilha leva você a uma clareira oculta, revelando uma vista deslumbrante do horizonte. "
            		+ "\nNo centro da clareira, você encontra uma caverna intrigante com um brilho misterioso em seu interior.");
        } else {
            JOptionPane.showMessageDialog(null, "Você corre para longe do monstro, escapando de seu alcance."
                    + "\nEnquanto corre, você sente que está sendo observado, mas continua a se afastar do perigo."
                    + "\nApós horas de fuga, " + player.getName() + " descobre uma trilha que se aprofunda na densa floresta.");

            JOptionPane.showMessageDialog(null, "A floresta é repleta de mistérios, e você não pode deixar de se perguntar sobre a origem do monstro que encontrou."
            		+ "\nExplorando mais a floresta, você avista uma trilha que se desvia do caminho principal. "
            		+ "\nCurioso(a) para descobrir para onde a trilha leva, você decide segui-la, ouvindo os sons suaves da natureza enquanto caminha."
            		+ "\nA trilha finalmente o leva a uma clareira oculta, onde você é recebido por uma vista panorâmica impressionante. "
            		+ "\nNo centro da clareira, uma caverna intrigante chama sua atenção com um brilho misterioso em seu interior.");
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
        String EasterEgg = JOptionPane.showInputDialog(null, "Indentificou algum nome especial?");
        if ("Naofumi".equalsIgnoreCase(EasterEgg) && "Escudeiro".equals(player.getPlayerClass())) {
        	Historia.EasterEgg(player);
        } else {
        	H_Situacional.Sequestro(player);
        }
    }
    
    public static void showPlayerInfo(Player player) {
        JOptionPane.showMessageDialog(null, "Jogador: " + player.getName() + "\nClasse: " + player.getPlayerClass()
                + "\nVida: " + player.getHealth() + "\nAtaque: " + player.getAttack() + "\nDefesa: " + player.getDefense());
    }

    public static void showInventory(Player player) {
        StringBuilder inventoryMessage = new StringBuilder("Inventário:");
        for (Item item : player.getInventory()) {
            inventoryMessage.append("\n").append(item.getName());
        }
        JOptionPane.showMessageDialog(null, inventoryMessage.toString());
    }
}
