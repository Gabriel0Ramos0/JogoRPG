package rpg;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Eventos {

    // Método para simular uma venda de item ao jogador
    public static void comprarItens(Player player) {
        JOptionPane.showMessageDialog(null, "Você encontra um comerciante amigável que está disposto a vender itens para você.");

        Item itemAVenda1 = new Item("Poção de Cura", 15, 5);
        Item itemAVenda2 = new Item("Poção de Mana", 15, 5);

        List<Item> itensDisponiveis = new ArrayList<>();
        itensDisponiveis.add(itemAVenda1);
        itensDisponiveis.add(itemAVenda2);

        JOptionPane.showMessageDialog(null, help());

        StringBuilder escolhaItens = new StringBuilder("Escolha os itens que deseja comprar:\n");
        for (int i = 0; i < itensDisponiveis.size(); i++) {
            escolhaItens.append(i + 1).append(". ").append(itensDisponiveis.get(i).getName())
                        .append(" - Valor: ").append(itensDisponiveis.get(i).getValue()).append("\n");
        }
        int[] indicesEscolhidos = obterIndicesItens(escolhaItens.toString(), itensDisponiveis.size());
        int custoTotal = calcularCustoTotal(itensDisponiveis, indicesEscolhidos);
        if (player.getCoins() >= custoTotal) {
            for (int indice : indicesEscolhidos) {
                player.getInventory().add(itensDisponiveis.get(indice));
            }
            player.decrementCoins(custoTotal);
            JOptionPane.showMessageDialog(null, "Você comprou o(s) " + obterNomesItens(indicesEscolhidos, itensDisponiveis) + "!");
        } else {
            JOptionPane.showMessageDialog(null, "Você não possui moedas suficientes para comprar esses itens.");
        }
    }

    // Método para simular uma compra de item pelo jogador
    public static void venderItens(Player player) {
        JOptionPane.showMessageDialog(null, "Você encontra um mercador disposto a comprar seus itens.");

        List<Item> itensParaVender = player.getInventory();
        JOptionPane.showMessageDialog(null, help());

        StringBuilder escolhaItens = new StringBuilder("Escolha os itens que deseja vender:\n");
        for (int i = 0; i < itensParaVender.size(); i++) {
            escolhaItens.append(i + 1).append(". ").append(itensParaVender.get(i).getName())
                        .append(" - Valor: ").append(itensParaVender.get(i).getValue()).append("\n");
        }
        int[] indicesEscolhidos = obterIndicesItens(escolhaItens.toString(), itensParaVender.size());
        List<String> nomesItensVendidos = new ArrayList<>();
        for (int indice : indicesEscolhidos) {
            if (indice >= 0 && indice < itensParaVender.size()) {
                nomesItensVendidos.add(itensParaVender.get(indice).getName());
            }
        }
        if (indicesEscolhidos.length > 0 && indicesEscolhidos[0] == -1) {
            JOptionPane.showMessageDialog(null, help());
            return;
        }

        for (int indice : indicesEscolhidos) {
            player.incrementCoins(itensParaVender.get(indice).getValue());
        }
        List<Item> itensVendidos = obterItensPorIndices(itensParaVender, indicesEscolhidos);
        player.getInventory().removeAll(itensVendidos);
        JOptionPane.showMessageDialog(null, "Você vendeu o(s) " + String.join(", ", nomesItensVendidos) + " por "
                                    + calcularCustoTotal(itensVendidos) + " moedas!");
    }

    private static String obterNomesItens(int[] indicesEscolhidos, List<Item> itens) {
        StringBuilder nomesItens = new StringBuilder();
        for (int indice : indicesEscolhidos) {
            if (indice >= 0 && indice < itens.size()) {
                nomesItens.append(itens.get(indice).getName()).append(", ");
            }
        }
        if (nomesItens.length() > 0) {
            nomesItens.setLength(nomesItens.length() - 2);
        }
        return nomesItens.toString();
    }

    private static int[] obterIndicesItens(String escolhaItens, int tamanho) {
        String input = JOptionPane.showInputDialog(null, escolhaItens);
        if (input == null || input.isEmpty()) {
            return new int[0];
        }
        String[] indicesStr = input.split(",");
        int[] indicesEscolhidos = new int[indicesStr.length];

        for (int i = 0; i < indicesStr.length; i++) {
            try {
                indicesEscolhidos[i] = Integer.parseInt(indicesStr[i].trim()) - 1;
                if (indicesEscolhidos[i] < 0 || indicesEscolhidos[i] >= tamanho) {
                    JOptionPane.showMessageDialog(null, "Índice inválido: " + (indicesEscolhidos[i] + 1));
                    return new int[0];
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida: " + indicesStr[i]);
                return new int[0];
            }
        }
        return indicesEscolhidos;
    }

    // Método auxiliar para calcular o custo total dos itens escolhidos
    private static int calcularCustoTotal(List<Item> itens, int[] indicesEscolhidos) {
        int custoTotal = 0;
        for (int indice : indicesEscolhidos) {
            custoTotal += itens.get(indice).getValue();
        }
        return custoTotal;
    }

    // Método auxiliar para calcular o custo total dos itens
    private static int calcularCustoTotal(List<Item> itens) {
        int custoTotal = 0;
        for (Item item : itens) {
            custoTotal += item.getValue();
        }
        return custoTotal;
    }

    // Método auxiliar para obter os itens escolhidos pelos índices
    private static List<Item> obterItensPorIndices(List<Item> itens, int[] indicesEscolhidos) {
        List<Item> itensEscolhidos = new ArrayList<>();
        for (int indice : indicesEscolhidos) {
            itensEscolhidos.add(itens.get(indice));
        }
        return itensEscolhidos;
    }

    // Método para simular a coleta de itens aleatórios
    public static void coletarItensAleatorios(Player player) {
        JOptionPane.showMessageDialog(null, "Enquanto explora a região, " + player.getName()
                                    + " encontra uma bolsa no chão, curioso, abre e encontrar alguns itens nele: ");

        Item item1 = new Item("Gema Preciosa", 50, 1);
        Item item2 = new Item("Planta Medicinal", 3, 5);
        Item item3 = new Item("Bolsa", 8, 1);

        player.getInventory().add(item1);
        player.getInventory().add(item2);
        player.getInventory().add(item3);

        JOptionPane.showMessageDialog(null, "Você coletou: " + item1.getName() + ", " + item2.getName() + " e " + item3.getName() + "!");
    }

    // Método auxiliar para escolher um item aleatório do inventário do jogador
    private static Item escolherItemAleatorio(List<Item> inventory) {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(inventory.size());
        return inventory.get(indiceAleatorio);
    }

    private static String help() {
        return "Para comprar ou vender mais de um item, basta colocar uma vírgula (,) depois de cada item escolhido!";
    }
}
