package rpg;

import javax.swing.JOptionPane;

public class H_Situacional {
	
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
	    			+ "\nSuas habilidades foram drenadas, e você se vê reduzido aos seus status iniciais."
	    			+ "\nA voz malévola dos capangas ressoa: 'Agora, ex-herói, você está despojado de seus poderes. Melromarc está condenada e nada pode detê-la. Aguarde a desgraça que virá!'");

	        resetarItensEStatus(player);
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
    	JOptionPane.showMessageDialog(null, "Teste de Continuação");
    	// Continuar história com fuga e novo início
    }
   
    private static void resetarItensEStatus(Player player) {
    	player.resetExperience();
        player.setAttack(5);
        player.setDefense(10);
        player.setTempDefense(10);
        player.setHealth(100);
        player.setMaxHealth(100);
        player.setCoins(0);
        player.getInventory().clear();
        Consumivel bread = new Consumivel("Pão", 2, 3, 10);
        player.getInventory().add(bread);
    }

}
