package rpg;

import javax.swing.JOptionPane;

public class H_Situacional {
	
	private static RPGGame game;
	static int levelantes = 0;
	static boolean marca = false;
	
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
	    	
	    	JOptionPane.showMessageDialog(null, "Ao recobrar a consciência, você se encontra em uma cela escura, desorientado e sem saber exatamente onde está. "
	    			+ "\nA atmosfera úmida e sufocante sugere que este lugar é um esconderijo subterrâneo sombrio, pertencente à nefasta Guilda dos Espectros do Véu."
	    			+ "\nEnquanto seus olhos se ajustam à penumbra, percebe seus pertences espalhados em uma mesa próxima. "
	    			+ "\nParece que seus captores menosprezaram o valor de seus equipamentos..."
	    			+ "\nOs capangas, figuras sinistras com olhares frios e intenções obscuras, explicam que capturaram você para minar a defesa de Melromarc, possibilitando que a guilda assuma o controle da cidade. "
	    			+ "\nConfiscam seus pertences, deixando você na cela fria e úmida, enquanto tramam seus planos malignos para Melromarc."
	    			+ "\nA sensação de impotência é avassaladora, mas a chama da determinação queimando dentro de você não se apaga. "
	    			+ "\nNum instante, as sombras ao seu redor ganham vida, dançando e torcendo-se como serpentes negras."
	    			+ "\nOs capangas da Guilda dos Espectros do Véu realizam um ritual obscuro e lançam sobre você um feitiço das sombras. Uma intensa onda de escuridão consome sua energia e poder.");
	    	JOptionPane.showMessageDialog(null, "Quando a névoa negra se dissipa, você percebe que algo mudou. "
	    			+ "\nSuas habilidades foram drenadas além do esperado (status iniciais redefinidos), e você se vê reduzido aos seus status iniciais."
	    			+ "\nAntes de apagar novamente, a voz malévola dos capangas ressoa: "
	    			+ "\n'Agora, ex-herói, você está despojado de seus poderes. Melromarc está condenada e nada pode detê-la. Aguarde que a desgraça que virá!'");
	    	
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
                + "\nO desafio está diante de você, e a Guilda dos Espectros do Véu subestimou a resiliência de um verdadeiro herói."
                + "\n\nEnquanto examina os detalhes da cela, seus olhos se fixam em algo peculiar: há um pequeno enigma desenhado discretamente na parede."
                + "\nAs letras formam uma mensagem codificada: 'Quem olha para as trevas, encontra o caminho para a luz.'"
                + "\nSerá este o caminho para escapar do cativeiro? A escolha é sua: continuar enfraquecendo as grades ou decifrar o enigma e explorar a passagem secreta.");
        
        Object[] opcoes = {"Procurar por objetos na cela", "Observar e Pensar"};
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
        
        JOptionPane.showMessageDialog(null, "Ao examinar os objetos, você percebe que há algo mais na caneca quebrada. "
        		+ "\nInclina-a delicadamente, e uma pequena chave cai em suas mãos."
                + "\nSua mente aguçada percebe que essa chave pode ser a resposta para a mensagem codificada nas paredes.");
        JOptionPane.showMessageDialog(null, "Com a chave em mãos, você retorna à mensagem nas paredes e, com astúcia, começa a decifrar os símbolos entalhados."
                + "\nCada letra é cuidadosamente analisada, e a mensagem codificada revela-se: 'Onde as sombras dançam, a liberdade aguarda.'");
        JOptionPane.showMessageDialog(null, "Inspirado pela revelação, você examina a cela com uma nova perspectiva. Em um canto escuro, percebe uma parede levemente deslocada."
                + "\nAo tocar a parede, você sente uma corrente de ar frio. Com cuidado, a parede revela-se uma passagem secreta. O caminho para a liberdade está à sua frente.");

        liberdade(player);
    }

    public static void observar(Player player) {
        Object[] opcoes = {"Procurar por objetos na cela", "Observar e Pensar"};        
        do {
            int escolha = JOptionPane.showOptionDialog(null, "Determinado a encontrar uma brecha na opressiva monotonia da cela, " + player.getName() + " decide observar e pensar. "
                    + "\nO tempo passa sem nenhum acontecimento relevante, e a cela permanece envolta em sombras, mas um pequeno brinho emerge no canto da cela "
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
                            + "\nforçando-o a reconsiderar sua estratégia. Após refletir, decide meditar para buscar forças internas. O brilho da parede continua");
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
    	Eventos eventos = new Eventos();
    	JOptionPane.showMessageDialog(null, "Com a colher enferrujada e a caneca quebrada em mãos, " + player.getName() + " vislumbra a oportunidade de escapar da cela."
    	        + "\nA cela parece mais opressora do que nunca, mas sua determinação e os itens improvisados dão um novo ânimo."
    	        + "\nEnquanto trabalha nas grades, você percebe que as barras estão cedendo aos poucos. A visão de um túnel estreito se abre diante de você, sugerindo uma rota de fuga."
    	        + "\nDe repente, você ouve passos pesados se aproximando, indicando a presença de guardas que se movem na sua direção."
    	        + "\nA tensão aumenta, e você se vê diante de uma decisão crucial: continuar com o plano original de enfraquecer as grades ou arriscar tudo explorando o túnel para escapar?"
    	        + "\nO tempo está se esgotando, e a escolha é sua.");
        Object[] opcoesFuga = {"Continuar enfraquecendo as grades", "Explorar a passagem secreta"};
        int escolhaFuga = JOptionPane.showOptionDialog(null, "Como você deseja prosseguir?", "FUGA IMINENTE", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesFuga, opcoesFuga[0]);

        if (escolhaFuga == 0) {
        	JOptionPane.showMessageDialog(null, "Decidindo seguir o plano original, você continua enfraquecendo as grades com a colher.");
        	JOptionPane.showMessageDialog(null, "O som dos passos dos guardas se intensifica, e cada momento conta. A adrenalina corre pelas suas veias enquanto você se esforça para apressar o processo."
        	        + "\nAgora, com as grades enfraquecidas, você decide arriscar e empurrá-las, criando um espaço suficiente para escapar da cela."
        	        + "\nDeslizando silenciosamente para fora da cela, você se encontra em um corredor escuro e estreito. "
        	        + "\nApenas a luz tênue de tochas distantes ilumina seu caminho, criando sombras dançantes ao seu redor."
        	        + "\nVocê percebe que está em território inimigo e precisa agir com cautela.");
        	marca = true;
        	player.marcarPassagemPorParteDaHistoria("PósFinal");
        	continuaLiberdade(false, player);
        } else {
        	JOptionPane.showMessageDialog(null, "Intrigado pela promessa de liberdade na passagem secreta, você decide explorá-la.");
        	JOptionPane.showMessageDialog(null, "Adentrando os túneis escuros, você se vê imerso em um labirinto subterrâneo que exala o cheiro pungente de esgoto e a umidade característica de dutos de ar."
        	        + "\nO eco dos seus passos ressoa pelas paredes de pedra, e você avança cautelosamente, mantendo-se atento a cada som ao seu redor."
        	        + "\nApós alguns minutos de caminhada, emerge em um corredor mais amplo, iluminado por uma luz fraca e distante."
        	        + "\nAo observar o corredor vazio à sua frente, percebe a oportunidade de avançar e desvendar o que aguarda no próximo trecho."
        	        + "\nDe repente, o som de passos apressados ressoa atrás de você, indicando que você não está sozinho. Uma sombra se aproxima rapidamente."
        	        + "\nDiante dessa ameaça iminente, você se prepara para um possível confronto.");
        	Monstro monstro = eventos.guarda();
        	Batalha batalha = new Batalha(game, player, monstro);
        	batalha.executarBatalhaEstendida(player, monstro);
        }        
    }
            
    public static void continuaLiberdade(boolean vitoria, Player player) {
	    if (vitoria == true) {
	    	JOptionPane.showMessageDialog(null, "Você derrotou o guarda.");
	    } else if (vitoria == false){		
	    	if (marca) {	
	    		
	    	} else {
	    	JOptionPane.showMessageDialog(null, "Infelizmente, o guarda o captura durante a batalha. Sua tentativa de fuga foi em vão.");
		    JOptionPane.showMessageDialog(null, "---GAME OVER---");
		    Historia.jogarNovamente();
		    return;
	    	}
	    }
	    //-----------------------------------------------------------------------------------------------------------------
        JOptionPane.showMessageDialog(null, "Você se aventura pelos corredores escuros, tentando encontrar uma rota segura.");

        // Descrição da tentativa de recuperar itens
        JOptionPane.showMessageDialog(null, "Durante sua fuga, você avista uma sala cheia de guardas onde alguns de seus pertences estão sendo mantidos.");
        Object[] opcoesItens = {"Tentar recuperar itens", "Deixar para trás"};
        int escolhaItens = JOptionPane.showOptionDialog(null, "Você avistou uma sala com seus pertences. O que deseja fazer?", "DECISÃO RÁPIDA", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesItens, opcoesItens[0]);

        if (escolhaItens == 0) {
            JOptionPane.showMessageDialog(null, "Você decide tentar recuperar seus itens, mesmo com o risco de chamar a atenção dos guardas.");

            // Adicionando aleatoriedade para determinar o sucesso na recuperação dos itens
            if (Math.random() < 0.5) {
                JOptionPane.showMessageDialog(null, "Com habilidade furtiva, você consegue recuperar seus pertences sem chamar a atenção dos guardas.");
                // Adicionando os itens de volta ao inventário do jogador
                // Exemplo: player.getInventory().add(itemRecuperado);
            } else {
                JOptionPane.showMessageDialog(null, "Infelizmente, sua tentativa de recuperar os itens chama a atenção dos guardas.");
                // Adicionar consequências narrativas, como perda de tempo, aumento da vigilância, etc.
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você decide deixar seus pertences para trás, evitando o risco de chamar a atenção dos guardas.");
            // Adicionar consequências narrativas, se necessário
        }

        // Descrição do cenário escuro após a tentativa de recuperar itens
        JOptionPane.showMessageDialog(null, "Continuando sua fuga, você se encontra em um corredor escuro e estreito. Apenas sua determinação guia você.");
    }

}