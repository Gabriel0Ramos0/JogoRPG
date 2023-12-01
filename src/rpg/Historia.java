package rpg;

import javax.swing.JOptionPane;

public class Historia {
	
	private static RPGGame game;
	
	public static void eventosMontanhasSombrias(Player player) {
		player.marcarPassagemPorParteDaHistoria("IgnoraCave");
		player.marcarPassagemPorParteDaHistoria("ForaCave");
        JOptionPane.showMessageDialog(null, "Ao se aproximar das Montanhas Sombrias, " + player.getName() + " percebe uma sensação familiar, e continua seguindo a trilha."
                + "\nA trilha íngreme leva a uma clareira onde destroços de uma estrutura desconhecida estão espalhados."
                + "\nInvestigando os destroços, " + player.getName() + " encontra pistas que sugerem um ritual mágico interrompido.");
        Eventos.coletarItensAleatoriosComHistorias(player);
        JOptionPane.showMessageDialog(null, "Seguindo adiante, " + player.getName() + " descobre um acampamento de treino, a fumaça era proveniente de uma fogueira com chamas altas."
                + "\nGuerreiros habilidosos estão treinando suas técnicas de combate, e um mestre de treinamento se aproxima de você.");
        JOptionPane.showMessageDialog(null, "Sem pensar muito " + player.getName() + " pergunta sobre o grande estrondo que escutou perto daqui."
        		+ "\nO mestre conta não ter ouvido nada, mas menciona que aqueles destroços foram de um ritual antigo..."
        		+ "\nSem deixar tempo para pensar, o mestre ofereçe ao "+ player.getName()+", se juntar aos treinos");
        String[] opcoesTreino = {"Juntar-se ao treinamento", "Continuar a jornada"};
        
        int escolhaTreino = JOptionPane.showOptionDialog(null, "O que você deseja fazer?", "Acampamento de Treino", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesTreino, opcoesTreino[0]);
        switch (escolhaTreino) {
            case 0:
                JOptionPane.showMessageDialog(null, player.getName() + " decide juntar-se ao treinamento."
                        + "\nO mestre de treinamento elogia sua coragem e concede a você uma técnica especial aprendida durante o treinamento."
                        + "\n Ataque +1 || Exeperiência +25");
                player.setAttack(player.getAttack() + 1);
                player.gainExperience(25);
                JOptionPane.showMessageDialog(null, "Com novas habilidades adquiridas, " + player.getName() + " agradece ao mestre e parte em sua jornada, "
                		+ "\nagora mais preparado para os desafios que o aguardam."
                		+ "\nOs guerreiros ali presentem, lhe entregam uma comida típica para ajudar em sua aventura!"
                		+ "\nGanhou 2 espetinho!");
                Consumivel espetinho = new Consumivel("Espetinho", 3, 2, 10);
                player.getInventory().add(espetinho);
                JOptionPane.showMessageDialog(null, "Com os suprimentos e boas vibrações dos guerreiros, " + player.getName() + " parte determinado para explorar mais desse mundo misterioso.");
                break;

            case 1:
                JOptionPane.showMessageDialog(null, player.getName() + " decide continuar sua jornada, agradecendo pelo acolhimento no acampamento de treino."
                        + "\nOs guerreiros desejam boa sorte em sua jornada e oferecem suprimentos para a viagem."
                        + "\nGanhou 2 espetinho!");
                Consumivel espetinhos = new Consumivel("Espetinho", 3, 2, 10);
                player.getInventory().add(espetinhos);
                
                JOptionPane.showMessageDialog(null, "Com os suprimentos e boas vibrações dos guerreiros, " + player.getName() + " parte determinado para explorar mais desse mundo misterioso.");
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. " + player.getName() + " fica indeciso sobre como interagir com o acampamento de treino.");
        }
        JOptionPane.showMessageDialog(null, "Pensando no que o mestre disse sobre as histórias passadas, "
        	    + player.getName() +" procura um meio de investigar mais a fundo sobre o ocorrido na montanha!");
        ContinuaHistoriaParte3(player);
    }
	
	public static void eventosCavernaDaPerdicao (Player player) {
		JOptionPane.showMessageDialog(null, player.getName() + " escolhe explorar mais a Caverna das Luminescências."
	            + "\nAo adentrar as profundezas da caverna, " + player.getName() + " sente a presença de algo sinistro."
	            + "\nA escuridão parece ganhar vida ao seu redor, e uma voz sussurra na mente de " + player.getName() + ", instigando-o a continuar.");

	    String[] opcoesContinuar = {"Continuar explorando", "Retornar à entrada da caverna"};
	    int escolhaContinuar = JOptionPane.showOptionDialog(null, "O que você deseja fazer?", "Presença Sinistra", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesContinuar, opcoesContinuar[0]);

	    switch (escolhaContinuar) {
	        case 0:
	            JOptionPane.showMessageDialog(null, player.getName() + " decide continuar explorando, seguindo o chamado sombrio."
	                    + "\nÀ medida que avança, a luz roxa dos cristais se transforma em uma luminosidade vermelha e pulsante.");
	            Historia historia = new Historia();
	            historia.confrontoMercadorSombrio(null, player);            
	            break;

	        case 1:
	            JOptionPane.showMessageDialog(null, player.getName() + " decide recuar e retornar à entrada da caverna."
	                    + "\nA sensação sinistra diminui à medida que se afasta das profundezas."
	                    + "\nAo sair da caverna, avista a fumaça na montanha e decide averiguar");
	            eventosMontanhasSombrias(player);
	            break;

	        default:
	            JOptionPane.showMessageDialog(null, "Opção inválida. " + player.getName() + " fica indeciso sobre como reagir à presença sinistra.");
	    }
	}
	
	private void confrontoMercadorSombrio(Boolean vitoria, Player player) {
		Eventos eventos = new Eventos();
	    JOptionPane.showMessageDialog(null, "De repente, " + player.getName() + " se depara com uma figura encapuzada, emergindo das sombras."
	            + "\nO mercador peculiar agora revela sua verdadeira forma, envolto em uma aura vermelha e sinistra."
	            + "\nEle se autodenomina o Mercador Sombrio, mestre dos artefatos corrompidos pela escuridão.");
	    
        Monstro monstro = eventos.bossMercador();
        Batalha batalha = new Batalha(game, player, monstro);
        batalha.executarBatalhaEstendida(player, monstro);
	}
        
	public static void continuaHistoriaCave(boolean vitoria, Player player) {	    
	    if (vitoria) {
	        JOptionPane.showMessageDialog(null, player.getName() + " derrota o Mercador Sombrio, dissipando a aura sinistra que envolvia a caverna."
	                + "\nA luz roxa dos cristais volta ao seu brilho normal.");
	        
	        JOptionPane.showMessageDialog(null, "Você encontra alguns itens deixados pelo Mercador Sombrio:"
	                + "\n- Chapéu de Mercador (Aumenta a Vida em 5)"
	                + "\n- Bife (Regenera 40 de Vida)");
	        
	        Equipavel chapeuDeMercador = new Equipavel("Chapéu do Mercador", 10, 1, 0, 0, 5);
	        player.equipItem(chapeuDeMercador);
	        Consumivel bife = new Consumivel("Bife", 10, 1, 40);
	        player.getInventory().add(bife);
	        
	        JOptionPane.showMessageDialog(null, player.getName() + " sai da caverna e avista uma montanha à distância."
	                + "\nIntrigado pela montanha e pelo barulho que tinha feito, " + player.getName() + " decide explorar e se aproximar dela.");
	        eventosMontanhasSombrias(player);
	    }
	}
        
	public static void eventosDesastreTerrivel(Player player) {
		Eventos eventos = new Eventos();
		player.marcarPassagemPorParteDaHistoria("Caverna");
	    JOptionPane.showMessageDialog(null, "Enquanto " + player.getName() + " se afasta da caverna, uma flexa mágica rasga o ar e o atinge nas costas."
	            + "\nA dor é intensa, e " + player.getName() + " se vê enfraquecido pela energia mágica da flexa.");
	    JOptionPane.showMessageDialog(null, player.getName() + " perde 20 de vida!");
	    player.setHealth(player.getHealth() - 20);

	    JOptionPane.showMessageDialog(null, "Você se sente apreensivo, como se algo maligno estivesse observando cada movimento seu."
	            + "\nMesmo ferido, " + player.getName() + " decide continuar sua jornada, agora com um fardo adicional a carregar e com medo do terreno ao redor.");

	    JOptionPane.showMessageDialog(null, "De repente, uma sombra gigante surge das Montanhas Sombrias, um monstro épico de olhos flamejantes."
	            + "\nEle dispara outra flexa, mas, com sorte, você consegue se esquivar no último momento.");
	    JOptionPane.showMessageDialog(null, "O monstro épico está furioso e avança em sua direção!");
	    
	    Monstro monstro = eventos.escolherMonstroAleatorioEpic();
        Batalha batalha = new Batalha(game, player, monstro);
        batalha.executarBatalhaEstendida(player, monstro);
        
        continuaHistoriaForaCave(false, player);
	}
	
	public static void continuaHistoriaForaCave(boolean vitoria, Player player) {	    
	    if (vitoria) {
		    JOptionPane.showMessageDialog(null, player.getName() + " emerge vitorioso da batalha épica contra o Monstro Épico."
		    		+ "\nA atmosfera pesada que se formou perto da Montanhas Sombrias começa a se dissipar, e você sente um alívio momentâneo.");
	        JOptionPane.showMessageDialog(null, "Agora que está tudo bem, você ouve seu nome vindo do alto da montanha. Intrigado, "
	        		+ player.getName() + " segue em direção a montanha e a fumaça que consegue ver.");
	        eventosMontanhasSombrias(player);
	    }
	}
	
	public static void ContinuaHistoriaParte3(Player player) {
		Eventos eventos = new Eventos();
		JOptionPane.showMessageDialog(null, "Após um tempo procurando por pistas, " + player.getName() +" encontra uma rachadura com passagem de ar."
				+ "\nDeterminado " + player.getName() + " avança mais fundo nas Montanhas Sombrias, superando obstáculos e desvendando mistérios."
	            + "\nA trilha leva a uma caverna escondida, cuja entrada está decorada com inscrições misteriosas.");
	    JOptionPane.showMessageDialog(null, "Ao entrar na caverna, " + player.getName() + " se depara com uma antiga câmara secreta."
	            + "\nNo centro, uma Ampulheta com fonte de energia mágica resplandece, iluminando a sala com uma luz cintilante.");
	    JOptionPane.showMessageDialog(null, "De repente, sombras se materializam ao redor da fonte e se fundem, tomando a forma de um ser ancestral."
	            + "\nEle se apresenta como o Guardião das Montanhas e revela uma profecia há muito tempo esquecida.");
	    JOptionPane.showMessageDialog(null, "'Aquele que chegar até aqui será testado, e sua jornada tomará um rumo inesperado."
	            + "\nVocê, " + player.getName() + ", é o escolhido para enfrentar o desafio que decidirá o destino destas terras.'");
	    int escolhaDesafio = JOptionPane.showOptionDialog(null, "O Guardião oferece a você um desafio. Aceita?",
	            "Desafio do Guardião", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	    if (escolhaDesafio == JOptionPane.YES_OPTION) {
	        JOptionPane.showMessageDialog(null, "Você aceita o desafio e é envolvido por uma luz intensa."
	                + "\nQuando a luz se dissipa, você se encontra em um local totalmente diferente, pronto para enfrentar o desafio do Guardião.");

	        Monstro monstro = eventos.bossDragao();
	        Batalha batalha = new Batalha(game, player, monstro);
	        
	    } else {
	        JOptionPane.showMessageDialog(null, "Ao recusar o desafio, o guardião agora enfurecido, avisa que você não está pronto!"
	        		+ "\n Sem perder tempo, o guardião lhe teleporta e quando a luz se dissipa, "
	        		+ "\n você se encontra em um local totalmente diferente, enfurecido por tal situação.");
	        
	        Monstro monstro = eventos.bossDragao();
	        Batalha batalha = new Batalha(game, player, monstro);
	    }
	    Historia.destinoFinal(player);
	}

	public static void destinoFinal(Player player) {
		
		JOptionPane.showMessageDialog(null, "Você emerge vitorioso da caverna, onde as paredes ecoam com o rugido dos monstros derrotados. "
        		+ "\nO guardião, admirando sua coragem, entrega-lhe uma armadura ancestral e uma poção grande (regenera 70 de vida) como reconhecimento por sua vitória. "
        		+ "\nEle fala sobre a tradição de fortalecer a armadura a cada batalha, uma herança dos heróis que enfrentaram as sombras que agora se erguem novamente.");
        Equipavel armaduraAncestral = new Equipavel("Armadura Ancestral", 10, 1, 0, 0, 1);
        player.equipItem(armaduraAncestral);
        
		JOptionPane.showMessageDialog(null, "Com sua nova armadura e sabedoria adquirida, você avança para as profundezas das Montanhas Sombrias."
		        + "\nÀ medida que avança, a escuridão se intensifica, e você descobre o terrível segredo por trás da ameaça crescente."
		        + "\nOs heróis antigos falharam em conter a Sombra Devoradora, uma entidade que ressurge para consumir tudo em seu caminho."
		        + "\nPreparado para o confronto final, você enfrenta a Sombra Devoradora em um campo de batalha sombrio e sinistro."
		        + "\nA criatura macabra, com olhos brilhando com malícia, se ergue para desafiar sua coragem."
		        + "\nA batalha é intensa, cada golpe trocado ressoando como um trovão nas profundezas da montanha."
		        + "\nA Sombra Devoradora, com sua defesa impenetrável, tenta consumir sua luz, mas a armadura ancestral brilha, resistindo à escuridão."
		        + "\nA poção grande, consumida estrategicamente, revigora suas forças quando mais necessário."
		        + "\nEnquanto você luta, lembranças das batalhas anteriores surgem, cada vitória e sacrifício moldando o herói que você se tornou."
		        + "\nA esperança, como uma luz tênue, queima em seu coração, alimentando sua determinação.");
		JOptionPane.showMessageDialog(null, "Enquanto você lembrava, sua espada misteriosa revela-se ser uma espada sagrada, podendo a cada golpe"
				+ "\nperfurar a armadura do seu inimigo. Agora com esta vantagem em mãos, " + player.getName() +" está mais determinado a colocar "
				+ "\num fim nas trevas!!!");
		JOptionPane.showMessageDialog(null, "Após alguns golpes bem sucedidos, as sombras da criatura lhe atacam com tanta violência, que sua espada"
				+ "\nperde a força da luz e sua perfuração. Mesmo sem seu bônus de ataque, " + player.getName() +" continua lutando.");
		Historia.iniciarBatalhaFinal(player);
	}
	
	public static void iniciarBatalhaFinal(Player player) {
		Eventos eventos = new Eventos();
		
	    Monstro monstro = eventos.bossFinal();
	    player.setHealth(player.getMaxHealth() / 2);
	    player.setDefense(player.getDefense() / 2);	    
	    Batalha batalha = new Batalha(game, player, monstro);
	    Historia.finalBatalhaFinal(false, player);
	}
	
	public static void finalBatalhaFinal(boolean vitoria, Player player) {
	    if (player.isAlive() && vitoria) {
	        JOptionPane.showMessageDialog(null, "Finalmente, com um golpe heroico, você derrota a Sombra Devoradora. "
	        		+ "\nA escuridão se dissipa, revelando uma cena de tranquilidade. "
	        		+ "\nVocê salvou o reino da iminente destruição. "
	        		+ "\nO equilíbrio é restaurado, e sua jornada chega ao fim.");
	        RPGGame.fimDaHistoria(player);
	    } else {
	        JOptionPane.showMessageDialog(null, "Infelizmente, você foi derrotado pela Sombra Devoradora. A escuridão consome o reino...");
	        JOptionPane.showMessageDialog(null, "---GAME OVER---");
	    }
	}

}


