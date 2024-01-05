package rpg;

import javax.swing.JOptionPane;

public class SideQuest {
	static RPGGame game;

    public static void mestreQuest(Player player) {    	
        JOptionPane.showMessageDialog(null, "Ao se aproximar do mestre, ele olha para " + player.getName() +
                " com um sorriso sábio. 'Ah, vejo que está de volta. Precisa de algo mais, ou está pronto para seguir em frente?'");

        String[] opcoes = {"Pedir trabalho generoso", "Seguir em frente"};
        int escolha = JOptionPane.showOptionDialog(null, "O que " + player.getName() + " deseja fazer?", "Escolha",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == 0) {
            JOptionPane.showMessageDialog(null, "'Há relatos de criaturas estranhas nas profundezas da floresta ao sul. "
                    + "\nDizem que essas criaturas patrulham por lá a muito tempo, misteriosamente guardando o local."
                    + "\nSe você conseguir investigar e trazer evidências, ficarei honrado em lhe entregar minhas adagas do silêncio."
                    + "\n......Eu mesmo que as fabriquei......'");

            JOptionPane.showMessageDialog(null, "O mestre, com uma expressão séria, adiciona: "
                    + "'E há algo mais, " + player.getName() + ". Eu preciso que recupere meu cinturão de forja mágica. "
                    + "\nSem ele, minha habilidade de forjar itens mágicos está comprometida. "
                    + "\nPassamos por aquelas terras até chegar aqui e ele foi perdido em uma batalha com uma dessas criaturas. "
                    + "\nSe trouxer o cinturão de volta, oferecerei recompensas adicionais pela sua coragem e serviço à forja.'");

            JOptionPane.showMessageDialog(null, "O mestre olha sério para " + player.getName() + " e antes de continuar fala: "
                    + "'Cuidado, " + player.getName() + ", a floresta ao sul é um lugar misterioso e perigoso. "
                    + "\nAs criaturas que lá habitam não são comuns, e algumas delas são conhecidas por sua agressividade. "
                    + "\nLeve equipamento adequado e esteja preparado para enfrentar desafios inesperados.");

            JOptionPane.showMessageDialog(null, "O mestre abaixa a voz como se estivesse compartilhando um segredo sombrio: "
                    + "'Além disso, ouvi rumores de que as trevas que se aproximam estão corrompendo as criaturas, tornando-as ainda mais perigosas. "
                    + "\nFique atento às mudanças no comportamento delas. Boa sorte, " + player.getName() + ".'");
            JOptionPane.showMessageDialog(null, "Antes de partir em direção à floresta ao sul, " + player.getName() + " retorna ao acampamento para pegar seus pertences. "
            		+ "\nO fogo crepitante lança sombras dançantes sobre as árvores, indicando que a noite se aproxima rapidamente.");
            JOptionPane.showMessageDialog(null, "Mesmo com a escuridão se aproximando, a determinação arde em seu coração, "
                    + "\nlevando-o a seguir viagem em busca do cinturão mágico. A floresta ao sul espera, repleta de desafios e mistérios.");
            buscandooCinturão(player);
        } else {
        	player.marcarPassagemPorParteDaHistoria("BuscandoRespostas");
        	JOptionPane.showMessageDialog(null, player.getName() + " por um instante pensou em perguntar algo, mas esqueceu.");
            JOptionPane.showMessageDialog(null, "Então, decidindo seguir em frente, " + player.getName() +
                    " se desculpa pelo equivoco e parte em direção ao seu destino final.");
            Historia.ContinuaHistoriaParte3(player);
        }
    }
    public static void buscandooCinturão(Player player) {
    	JOptionPane.showMessageDialog(null, "Sob o manto noturno, você adentra a floresta ao sul, uma escuridão profunda que parece devorar a luz da lua. "
                + "\nUm silêncio aterrorizante se instala, como se a própria natureza estivesse contendo a respiração. "
                + "\nMais adiante, as enormes vegetações da floresta parecem se fechar, obscurecendo o caminho e lançando sombras ameaçadoras.");
        JOptionPane.showMessageDialog(null, "Enquanto avança pelo desconhecido, você percebe um fenômeno perturbador. A escuridão, uma presença apocalíptica, "
                + "\nse manifesta de maneira sinistra. Criaturas deformadas, como zumbis retorcidos, começam a se agitar nas sombras, ameaçando se aproximar.");
        JOptionPane.showMessageDialog(null, "De repente, criaturas zumbis emergem das sombras, suas formas distorcidas movendo-se com um propósito sinistro. "
                + "\nVocê está prestes a enfrentar 3 desses seres apocalípticos!");
        batalhasSide(player);
    }
    
    public static void continuaBusca(boolean vitoria, Player player) {
    	if (vitoria) {
    		JOptionPane.showMessageDialog(null, player.getName() + " emerge vitorioso das batalhas, exausto, mas determinado a alcançar seu objetivo. "
                    + "\nApós algumas horas de exploração e combate, você avista ao longe uma luz fraca entre as árvores. "
                    + "\nAo se aproximar, você percebe que a luz emana de um objeto brilhante - é o cinturão mágico que o mestre forjador tanto deseja.");

            JOptionPane.showMessageDialog(null, "O cinturão está suspenso em um altar de pedra no centro de um pequeno claro na floresta. "
                    + "\nAo se aproximar, você percebe que algo está errado. O cinturão parece estar sendo protegido por uma presença mágica, "
                    + "ou talvez por uma criatura guardiã. Antes de você alcançar o altar, precisa tomar uma descisão");
            
            String[] opcoes = {"Ir e pegar", "Iluminar o local primeiro"};
            int escolha = JOptionPane.showOptionDialog(null, "Como " + player.getName() + " deseja prosseguir?", "Escolha",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            if (escolha == 0) {
            	JOptionPane.showMessageDialog(null, player.getName() + " descide simplesmente pegar o cinturão."
            			+ "\nApós pegar e se virar para ir embora, monstros gigantes se materializam das sombras e estão jurados a recuperar o material brilhante.\n"
            			+ player.getName() + " tinha que ter lembrado que o cinturão foi perdido em batalha e não deveria estar em um altar.");
            	Eventos eventos = new Eventos();
            	Monstro monstro = eventos.bossSombra();
                Batalha batalha = new Batalha(game, player, monstro);
                batalha.executarBatalhaEstendida(player, monstro);
            } else if (escolha == 1) {
                JOptionPane.showMessageDialog(null, "Optando por uma abordagem cautelosa, você decide iluminar o local antes de se aproximar do cinturão. "
                        + "\nCom a fonte de luz, as sombras recuam e você consegue pegar o cinturão sem desencadear nenhuma criatura guardiã.");
                player.gainExperience(20);
                JOptionPane.showMessageDialog(null, "Com o cinturão mágico agora em suas mãos, você decide retornar ao mestre forjador e entregar o artefato, "
                        + "\ncompletando assim a missão com sucesso.");
                player.marcarPassagemPorParteDaHistoria("miniboss2");
                player.marcarPassagemPorParteDaHistoria("Recuperou");
                mestreQuestConclusao(player);
            }
    	}
    }
    
    public static void mestreQuestConclusao(Player player) {
        JOptionPane.showMessageDialog(null, "Agora com a vitória e o cinturão, é preciso retornar ao acampamento."
        		+ "\nAndando na vasta mata, "+ player.getName() +" se depara com um local protegido pelas tochas de um acampamento abandonado recentemente."
        	    + "\nO local parecia tranquilo, então " + player.getName() + " passa a noite por lá."
                + "\nAo amanhecer, você parte para o acampamento do mestre, ansioso para entregar o cinturão mágico que recuperou.");
        if (player.jaPassouPorParteDaHistoria("Recuperou")) {
            JOptionPane.showMessageDialog(null, "Ao chegar ao acampamento, você é recebido pelo mestre forjador. Ele percebe o cinturão mágico em suas mãos "
                    + "e um sorriso de satisfação se forma em seu rosto.");
            JOptionPane.showMessageDialog(null, "Mestre Forjador: Excelente trabalho, " + player.getName() + "! Você não apenas sobreviveu às provações, mas trouxe o cinturão mágico de volta. "
                    + "\nEste artefato é de grande valor!!");
            JOptionPane.showMessageDialog(null, "Mestre Forjador: Sua coragem e determinação são dignas de um verdadeiro herói. "
            		+ "\nComo recompensa, permita-me lhe entregar o prometido e melhorar seus equipamentos. "
                    + "\nVocê merece algo especial pela sua conquista.");
            Equipavel adagasSilenciosas = new Equipavel("Adagas do Silêncio", 45, 1, 5, 0, 0);
	        player.equipItem(adagasSilenciosas);
	        player.setAttack(player.getAttack() + 1);
        	player.setDefense(player.getDefense() + 2);
        	player.setTempDefense(player.getTempDefense() + 2);
            JOptionPane.showMessageDialog(null, "Mestre Forjador: Agora, descanse um pouco antes de embarcar em novas jornadas. Quando estiver pronto para enfrentar desafios ainda maiores, "
                    + "\nvenha me procurar. Estarei aqui, forjando armas e preparando heróis para o confronto vindouro.");
            JOptionPane.showMessageDialog(null, "Parabéns, " + player.getName() + "! Você concluiu com sucesso a missão e fortaleceu sua jornada como herói."
            		+ "\nRecompensa bonûs: + 25XP");
            player.gainExperience(25);
        }
        JOptionPane.showMessageDialog(null, "Após descansar algumas horas, " + player.getName() + " decide buscar por mais pistas na montanha.");
        Historia.ContinuaHistoriaParte3(player);
    }

    
    public static void batalhasSide(Player player) {
    	Eventos eventos = new Eventos();
    	Monstro monstro = eventos.escolherMonstroAleatorioEpicSide();        
		new Batalha(game, player, monstro);		
    }
    
    public static void miniboss2(Player player) {
    	Eventos eventos = new Eventos();
    	Monstro monstro = eventos.bossObsidea();
        Batalha batalha = new Batalha(game, player, monstro);
        batalha.executarBatalhaEstendida(player, monstro);		
    }
}

