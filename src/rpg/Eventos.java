package rpg;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.Random;

public class Eventos {

    // Método para simular uma venda de item ao jogador
    public static void venderItem(Player player) {
        // Substitua este bloco de código com a lógica real da venda
        JOptionPane.showMessageDialog(null, "Você encontra um comerciante amigável que está disposto a vender um item.");

        // Exemplo de item para venda
        Item itemAVenda = new Item("Poção de Cura", 10, 5);

        int escolha = JOptionPane.showConfirmDialog(null, "Deseja comprar " + itemAVenda.getName() + " por " + itemAVenda.getValue() + " moedas?");
        if (escolha == JOptionPane.YES_OPTION) {
            // Verifica se o jogador possui moedas suficientes
            if (player.getCoins() >= itemAVenda.getValue()) {
                // Realiza a compra
                player.getInventory().add(itemAVenda);
                player.decrementCoins(itemAVenda.getValue());
                JOptionPane.showMessageDialog(null, "Você comprou " + itemAVenda.getName() + "!");
            } else {
                JOptionPane.showMessageDialog(null, "Você não possui moedas suficientes para comprar este item.");
            }
        }
    }

    // Método para simular uma compra de item pelo jogador
    public static void comprarItem(Player player) {
        // Substitua este bloco de código com a lógica real da compra
        JOptionPane.showMessageDialog(null, "Você encontra um mercador disposto a comprar um de seus itens.");

        // Exemplo de item para compra
        Item itemParaVender = escolherItemAleatorio(player.getInventory());

        int escolha = JOptionPane.showConfirmDialog(null, "Deseja vender " + itemParaVender.getName() + " por " + itemParaVender.getValue() + " moedas?");
        if (escolha == JOptionPane.YES_OPTION) {
            // Realiza a venda
            player.getInventory().remove(itemParaVender);
            player.incrementCoins(itemParaVender.getValue());
            JOptionPane.showMessageDialog(null, "Você vendeu " + itemParaVender.getName() + " por " + itemParaVender.getValue() + " moedas!");
        }
    }

    // Método para simular a coleta de itens aleatórios
    public static void coletarItensAleatorios(Player player) {
        JOptionPane.showMessageDialog(null, "Enquanto explora a região, "+ player.getName()+ " encontra uma bolsa no chão, curioso, abre e encontrar alguns itens nele");

        Item item1 = new Item("Gema Preciosa", 50, 1);
        Item item2 = new Item("Planta Medicinal", 3, 5);
        Item item3 = new Item("Bolsa", 8, 1);

        player.getInventory().add(item1);
        player.getInventory().add(item2);
        player.getInventory().add(item3);

        JOptionPane.showMessageDialog(null, "Você coletou alguns itens: " + item1.getName() + ", " + item2.getName() + " e " + item3.getName()+ "!");
    }

    // Método auxiliar para escolher um item aleatório do inventário do jogador
    private static Item escolherItemAleatorio(List<Item> inventory) {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(inventory.size());
        return inventory.get(indiceAleatorio);
    }
}
