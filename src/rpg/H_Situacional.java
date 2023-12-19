package rpg;

import javax.swing.JOptionPane;

public class H_Situacional {
	
	static int levelantes = 0;
	
	public static void Sequestro(Player player) {		
	    if (player.getCoins() >= 150 || hasMedalhaoMelromarc(player)) {
	    	if (player.jaPassouPorParteDaHistoria("AchouEgg")) {
	    	    JOptionPane.showMessageDialog(null, "Passou-se algum tempo desde que você encontrou a sala do mural dos heróis, e Melromarc floresceu sob sua proteção."
	    	    		+ "\nUm dia, ao caminhar pelas ruas da cidade, você é abordado por um mensageiro real. Seu semblante carrega a urgência de uma mensagem importante."
	    	    		+ "\nO mensageiro informa que uma guilda rival, os Espectros do Véu, arquitetou um plano para tomar o controle de Melromarc. "
	    	    		+ "\nSeus líderes sombrios desejam subjugar a cidade e você, como herói lendário, é um alvo crucial para os planos nefastos deles."
	    	    		+ "\nO mensageiro pede que você se prepare para um iminente ataque, pois a Guilda dos Espectros do Véu está se aproximando rapidamente, com suas forças ocultas nas sombras."
	    	    		+ "\nDeterminado a lidar com essa ameaça, você avança em direção à guilda. "
	    	    		+ "\nEnquanto caminhava pelas ruas, o sol lançava uma luz dourada sobre Melromarc, destacando a beleza da cidade que você jurou proteger."
	    	    		+ "\nNo entanto, a paz é abruptamente interrompida quando capangas da Guilda dos Espectros do Véu, habilmente escondidos, emergem das sombras."
	    	    		+ "\nSeu ataque é rápido e feroz, e antes que você possa reagir completamente, eles conseguem te capturar."
	    	    		+ "\nVocê é levado para um local desconhecido pelos capangas da guilda rival, cujos olhos ardilosos sugerem planos maiores. "
	    	    		+ "\nEles pretendem desestabilizar Melromarc, lançando a cidade em caos e temor.");
	    	    JOptionPane.showMessageDialog(null, "Sem deixar você raciocinar, te apagam...");
	    	} else {
	    	    JOptionPane.showMessageDialog(null, "Depois de derrotar o mal que ameaçava Melromarc, o reino desfruta de um período de paz graças aos seus esforços heróicos."
	    	    		+ "\nEntretanto, a paz é interrompida quando você é surpreendido por capangas da Guilda dos Espectros do Véu, uma guilda rival notória."
	    	    		+ "\nEles conseguem te capturar e levam você para um local desconhecido, planejando desestabilizar Melromarc.");
	    	    JOptionPane.showMessageDialog(null, "Sem deixar você raciocinar, te apagam...");
	    	}
	    	JOptionPane.showMessageDialog(null, "Ao recobrar a consciência, você se vê em uma cela escura, desorientado e sem saber exatamente onde está."
	    		    + "\nA atmosfera úmida e sufocante sugere que este lugar é um esconderijo subterrâneo sombrio, pertencente à nefasta Guilda dos Espectros do Véu."
	    		    + "\nAo examinar o ambiente, percebe que as paredes estão revestidas de sombras, ecoando a sinistra reputação da guilda."
	    		    + "\nOs capangas, figuras sinistras com olhares frios e intenções obscuras, explicam que capturaram você para minar a defesa de Melromarc, "
	    		    + "\npossibilitando que a guilda assuma o controle da cidade.");	    		    
	    	JOptionPane.showMessageDialog(null, "Confiscam seus pertences, deixando você na cela fria e úmida, enquanto tramam seus planos malignos para Melromarc."
	    		    + "\nA sensação de impotência é avassaladora, mas a chama da determinação queimando dentro de você não se apaga.");
	    	JOptionPane.showMessageDialog(null, "Num instante, as sombras ao seu redor ganham vida, dançando e torcendo-se como serpentes negras. "
	    			+ "\nOs capangas da Guilda dos Espectros do Véu realizam um ritual obscuro e lançam sobre você um feitiço das sombras."
	    			+ "\nVocê sente uma intensa onda de escuridão consumindo sua energia e poder. Quando a névoa negra se dissipa, você percebe que algo mudou. "
	    			+ "\nSuas habilidades foram drenadas além do esperado (status iniciais redefinidos), e você se vê reduzido aos seus status iniciais e, antes de apagar novamente,"
	    			+ "\na voz malévola dos capangas ressoa: 'Agora, ex-herói, você está despojado de seus poderes. Melromarc está condenada e nada pode detê-la. Aguarde que a desgraça que virá!'");
	    	
	    	levelantes = (player.getLevel() - 1) * (10 + (player.getLevel() - 1) * 10) / 2;
	        Player.resetarItensEStatus(player);
	        RPGGame.showPlayerInfo(player);
	        RPGGame.showInventory(player);
	        novaJornada(player);
	    } else {
	        Historia.jogarNovamente();
	    }
	}

    private static boolean hasMedalhaoMelromarc(Player player) {
        for (Item item : player.getInventory()) {
            if (item.getName().equals("Medalhão de Melromarc")) {
                return true;
            }
        }
        return false;
    }
    
    public static void novaJornada(Player player) {
        JOptionPane.showMessageDialog(null, "Você recobra a consciência novamente, sentindo-se grogue e desorientado. "
                + "\nA escuridão ao seu redor parece pulsar com uma energia sinistra, enquanto os murmúrios sussurrantes dos "
                + "\ncapangas da Guilda dos Espectros do Véu ecoam nas paredes úmidas e sombrias."
                + "\nApenas a luz fraca de uma tocha distante revela os contornos de sua cela apertada. "
                + "\nCautelosamente, você se levanta, sentindo uma fraqueza profunda em seus membros. "
                + "\nAs memórias do ataque e do feitiço das sombras ainda estão frescas em sua mente.");
        JOptionPane.showMessageDialog(null, "Ao explorar a cela, você encontra indícios de outros prisioneiros que compartilharam destinos similares. "
                + "\nArranhões nas paredes, marcas de desespero, testemunham a luta deles por liberdade. "
                + "\nEm um canto empoeirado, há uma mensagem rabiscada: 'A sombra não consome aquele que persiste. A esperança é nossa luz e o medo nosso inimigo.'");
        JOptionPane.showMessageDialog(null, "Essa descoberta, por mais singela que seja, reaviva sua determinação. "
                + "\nVocê se dá conta de que a fuga não é apenas uma questão de força, mas de engenho e paciência. "
                + "\nO desafio está diante de você, e a Guilda dos Espectros do Véu subestimou a resiliência de um verdadeiro herói.");

        Object[] opcoes = {"Procurar por objetos na cela", "Observar e Esperar"};
        int escolha = JOptionPane.showOptionDialog(null, "Como você pretende agir?", "A FUGA", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == 0) {
            procurarObjetos(player);
        } else {
            observar(player);
        }
    }

    public static void procurarObjetos(Player player) {
    	JOptionPane.showMessageDialog(null, "Determinado a encontrar recursos que possam ser cruciais para sua fuga, " + player.getName() + " explora cada canto escuro e úmido da cela."
                + "\nEntre a escuridão, seus olhos capturam o brilho sutil de uma pequena colher enferrujada e uma caneca quebrada."
                + "\nApesar de sua aparência modesta, a colher e a caneca, agora em suas mãos, parecem conter um potencial oculto.");

        Item colher = new Item("Colher Enferrujada", 2, 1, "Vendível");
        Item caneca = new Item("Caneca Quebrada", 2, 1, "Vendível");
        player.getInventory().add(colher);
        player.getInventory().add(caneca);

        liberdade(player);
    }

    public static void observar(Player player) {
        Object[] opcoes = {"Procurar por objetos na cela", "Observar e Esperar"};        
        do {
            int escolha = JOptionPane.showOptionDialog(null, "Determinado a encontrar uma brecha na opressiva monotonia da cela, " + player.getName() + " decide observar e esperar. "
                    + "\nO tempo passa sem nenhum acontecimento relevante, e a cela permanece envolta em sombras. "
                    + "\nDiante dessa quietude, você se vê obrigado a tomar uma decisão novamente.", "A FUGA", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
            if (escolha == 0) {
                procurarObjetos(player);
                break;
            } else {
                Object[] opcoesMeditacao = {"Meditar para recobrar poderes", "Continuar Observando"};
                int escolhaMeditacao = JOptionPane.showOptionDialog(null, "Enquanto observa, uma sutil energia parece dançar ao seu redor. O que deseja fazer?", "MEDITAÇÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesMeditacao, opcoesMeditacao[0]);
                if (escolhaMeditacao == 0) {
                    meditar(player);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Persistindo na observação, você aguarda pacientemente por alguma oportunidade. No entanto, o silêncio prevalece, "
                            + "\nforçando-o a reconsiderar sua estratégia. Após refletir, decide meditar para buscar forças internas.");
                    meditar(player);
                    break;
                }
            }
        } while (true);
    }

    public static void meditar(Player player) {
        if (player.jaPassouPorParteDaHistoria("MEDITACAO")) {
            JOptionPane.showMessageDialog(null, "Você já tentou meditar antes, sem sucesso. Talvez seja hora de procurar objetos na cela.");
            procurarObjetos(player);
            return;
        }
        JOptionPane.showMessageDialog(null, "Determinado a encontrar paz interior e recobrar seus poderes, " + player.getName() + " decide meditar. "
        		+ "\nFecha os olhos, concentra-se e busca conexão com a energia ao seu redor.");
        if (Math.random() < 0.1) {
            JOptionPane.showMessageDialog(null, "Em meio à meditação, uma onda de energia revitalizante percorre seu corpo. "
            		+ "\nUma conexão mística é estabelecida, permitindo que você recupere seus níveis de experiência!");
            player.gainExperience(levelantes);
            JOptionPane.showMessageDialog(null, "Agora, fortalecido pelos poderes reanimados, você decide explorar a cela em busca de objetos para a fuga.");
            procurarObjetos(player);
        } else {
            JOptionPane.showMessageDialog(null, "No silêncio da meditação, o poder sombrio da cela impede que você recupere seus poderes. "
            		+ "\nDecepcionado, " + player.getName() + " decide procurar itens na cela.");
            procurarObjetos(player);
        }
        player.marcarPassagemPorParteDaHistoria("MEDITACAO");
    }

    public static void liberdade(Player player) {
        JOptionPane.showMessageDialog(null, "Teste");
        
    }
}