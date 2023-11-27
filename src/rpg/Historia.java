package rpg;

import javax.swing.JOptionPane;

public class Historia {
	
	public void eventosMontanhasSombrias(Player player) {
        JOptionPane.showMessageDialog(null, "Ao se aproximar das Montanhas Sombrias, " + player.getName() + " percebe uma atmosfera pesada e sinistra."
                + "\nA trilha íngreme leva a uma clareira onde destroços de uma estrutura desconhecida estão espalhados."
                + "\nInvestigando os destroços, " + player.getName() + " encontra pistas que sugerem um ritual mágico interrompido.");
        // Adicionando nova situação
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
                
                // Adicione aqui os benefícios ou eventos específicos do treinamento.

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
		
	}

	public static void eventosDesastreTerrivel (Player player) {
	
	}
}
