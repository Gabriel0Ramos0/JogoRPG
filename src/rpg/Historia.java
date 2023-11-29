package rpg;

import javax.swing.JOptionPane;

public class Historia {
	
	private RPGGame game;
	
	public static void eventosMontanhasSombrias(Player player) {
        JOptionPane.showMessageDialog(null, "Ao se aproximar das Montanhas Sombrias, " + player.getName() + " percebe uma atmosfera pesada e sinistra."
                + "\nA trilha íngreme leva a uma clareira onde destroços de uma estrutura desconhecida estão espalhados."
                + "\nInvestigando os destroços, " + player.getName() + " encontra pistas que sugerem um ritual mágico interrompido.");
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
                        + "\nO mestre de treinamento elogia sua coragem e concede a você uma técnica especial aprendida durante o treinamento.");
                //Recompensa de Treino
                player.gainExperience(25);

                JOptionPane.showMessageDialog(null, "Com novas habilidades adquiridas, " + player.getName() + " agradece ao mestre e parte em sua jornada, agora mais preparado para os desafios que o aguardam.");
                break;

            case 1:
                JOptionPane.showMessageDialog(null, player.getName() + " decide continuar sua jornada, agradecendo pelo acolhimento no acampamento de treino."
                        + "\nOs guerreiros desejam boa sorte em sua jornada e oferecem suprimentos para a viagem.");
                
                // Adicione aqui os benefícios ou eventos específicos de continuar a jornada.

                JOptionPane.showMessageDialog(null, "Com os suprimentos e boas vibrações dos guerreiros, " + player.getName() + " parte determinado para explorar mais desse mundo misterioso.");
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. " + player.getName() + " fica indeciso sobre como interagir com o acampamento de treino.");
        }
        JOptionPane.showMessageDialog(null, "Com essa descoberta, " + player.getName() + " decide retornar à Caverna das Luminescências e compartilhar suas descobertas.");
    }
	
	public static void eventosCavernaDaPerdicao (Player player) {
		Eventos eventos = new Eventos();
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

	public static void eventosDesastreTerrivel (Player player) {
	
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
        	Eventos eventos = new Eventos();
        	Historia historias = new Historia();
	    if (vitoria) {
	        JOptionPane.showMessageDialog(null, player.getName() + " derrota o Mercador Sombrio, dissipando a aura sinistra que envolvia a caverna."
	                + "\nA luz roxa dos cristais volta ao seu brilho normal.");
	        
	        // Pode adicionar recompensas específicas por derrotar o Mercador Sombrio

	    } else {
	        JOptionPane.showMessageDialog(null, player.getName() + " é derrotado pelo poder sombrio do Mercador."
	                + "\nA escuridão consome " + player.getName() + " enquanto ele desmaia na caverna.");
	        // Adote as consequências de ser derrotado, como retorno à entrada ou outro desenvolvimento na história.
	    }
	}
}
