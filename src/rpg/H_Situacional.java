package rpg;

public class H_Situacional {

    public static void Sequestro(Player player) {
        if (player.getCoins() >= 150 || hasMedalhaoMelromarc(player)) {
            // O jogador tem moedas suficientes ou o Medalhão de Melromarc, então ele evita o sequestro.
        	System.out.println("Você foi sequestrado por uma gangue de saqueadores!");
            resetarItensEStatus(player);
            RPGGame.showPlayerInfo(player);
            RPGGame.showInventory(player);
        } else {
        	
        }
    }

    // Adicionar continuação de história com a ideia de ser sequestrado e preso por uma gangue de saqueadores, perdendo itens e resetando Status
    // Para seguir nesta história, o player precisa guardar o medalhão da família de Melromarc ou portar consigo 150 moedas de ouro.

    private static boolean hasMedalhaoMelromarc(Player player) {
        // Verifica se o jogador possui o Medalhão de Melromarc no inventário.
        for (Item item : player.getInventory()) {
            if (item.getName().equals("Medalhão de Melromarc")) {
                return true;
            }
        }
        return false;
    }

    private static void resetarItensEStatus(Player player) {
    	player.setAttack(5);
        player.setDefense(10);
        player.setTempDefense(10);
        player.setCoins(0);
        player.setInventory(null);
    }
}
