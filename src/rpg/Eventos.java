package rpg;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Eventos {

	// Método para simular uma compra de item pelo jogador
	public static void comprarItens(Player player) {
	    JOptionPane.showMessageDialog(null, "Você encontra um comerciante amigável que está disposto a vender itens para você.");

	    Consumivel itemAVenda1 = new Consumivel("Poção de Cura", 15, 5, 25);
	    Consumivel itemAVenda2 = new Consumivel("Poção de Mana", 15, 5, 25);
	    Item itemAVenda3 = new Item("Minério de Ametista", 32, 2, "Vendível");
	    Item itemAVenda4 = new Item("Espada Misteriosa", 47, 1, "Vendível");
	    Item itemAVenda5 = new Item("Botas para neve (com cristal)", 75, 1, "Vendível");

	    List<Item> itensDisponiveis = new ArrayList<>();
	    itensDisponiveis.add(itemAVenda1);
	    itensDisponiveis.add(itemAVenda2);
	    itensDisponiveis.add(itemAVenda3);
	    itensDisponiveis.add(itemAVenda4);
	    itensDisponiveis.add(itemAVenda5);

	    StringBuilder escolhaItens = new StringBuilder("Escolha os itens que deseja comprar:\n");
	    for (int i = 0; i < itensDisponiveis.size(); i++) {
	        Item item = itensDisponiveis.get(i);
	        escolhaItens.append(i + 1).append(". ").append(item.getName())
	                    .append(" - Valor: ").append(item.getValue())
	                    .append(" - Disponível: ").append(item.getQuantity()).append("\n");
	    }

	    int[] indicesEscolhidos = obterIndicesItens(escolhaItens.toString(), itensDisponiveis.size());
	    int custoTotal = calcularCustoTotal(itensDisponiveis, indicesEscolhidos);

	    Map<String, Integer> quantidadePorItem = new HashMap<>();

	    if (player.getCoins() >= custoTotal) {
	        for (int indice : indicesEscolhidos) {
	            Item itemEscolhido = itensDisponiveis.get(indice);
	            player.getInventory().add(itemEscolhido);
	            itemEscolhido.decrementQuantity();
	            quantidadePorItem.put(itemEscolhido.getName(), quantidadePorItem.getOrDefault(itemEscolhido.getName(), 0) + 1);
	        }
	        player.decrementCoins(custoTotal);
	        StringBuilder mensagemCompra = new StringBuilder("Você comprou: ");

	        for (Map.Entry<String, Integer> entry : quantidadePorItem.entrySet()) {
	            mensagemCompra.append(entry.getValue()).append(" ").append(entry.getKey());
	            if (entry.getValue() > 1) {
	                mensagemCompra.append("s");
	            }
	            mensagemCompra.append(", ");
	        }

	        if (mensagemCompra.length() > 0) {
	            mensagemCompra.setLength(mensagemCompra.length() - 2); // Remove a última vírgula
	        }

	        JOptionPane.showMessageDialog(null, mensagemCompra.toString() + "!");
	    } else {
	        JOptionPane.showMessageDialog(null, "Você não possui moedas suficientes para comprar esses itens.");
	    }
	}

	// Método para simular uma compra de item pelo jogador
	public static void venderItens(Player player) {
	    JOptionPane.showMessageDialog(null, "Você encontra um mercador disposto a comprar seus itens.");

	    List<Item> itensParaVender = player.getInventory();

	    StringBuilder escolhaItens = new StringBuilder("Escolha os itens que deseja vender:\n");
	    for (int i = 0; i < itensParaVender.size(); i++) {
	        Item item = itensParaVender.get(i);
	        escolhaItens.append(i + 1).append(". ").append(item.getName())
	                    .append(" - Valor: ").append(item.getValue())
	                    .append(" - Quantidade: ").append(item.getQuantity()).append("\n");
	    }

	    int[] indicesEscolhidos = obterIndicesItens(escolhaItens.toString(), itensParaVender.size());

	    Map<String, Integer> quantidadePorItem = new HashMap<>();
	    List<Item> itensVendidos = new ArrayList<>();

	    for (int indice : indicesEscolhidos) {
	        if (indice >= 0 && indice < itensParaVender.size()) {
	            Item itemVendido = itensParaVender.get(indice);
	            player.incrementCoins(itemVendido.getValue());
	            itemVendido.decrementQuantity();
	            itensVendidos.add(itemVendido);
	            quantidadePorItem.put(itemVendido.getName(), quantidadePorItem.getOrDefault(itemVendido.getName(), 0) + 1);
	        }
	    }

	    StringBuilder mensagemVenda = new StringBuilder("Você vendeu: ");
	    for (Map.Entry<String, Integer> entry : quantidadePorItem.entrySet()) {
	        mensagemVenda.append(entry.getValue()).append(" ").append(entry.getKey());
	        if (entry.getValue() > 1) {
	            mensagemVenda.append("s");
	        }
	        mensagemVenda.append(", ");
	    }
	    if (mensagemVenda.length() > 0) {
	        mensagemVenda.setLength(mensagemVenda.length() - 2);
	    }
	    JOptionPane.showMessageDialog(null, mensagemVenda.toString() + " por " + calcularCustoTotal(itensVendidos) + " moedas!");
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
    
    // Método auxiliar para escolher um item aleatório do inventário do jogador
    private static Item escolherItemAleatorio(List<Item> inventory) {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(inventory.size());
        return inventory.get(indiceAleatorio);
    }
    
    // Método para simular a coleta de itens aleatórios com histórias e efeitos
    public static void coletarItensAleatoriosComHistorias(Player player) {
        JOptionPane.showMessageDialog(null, "Enquanto explora a região, " + player.getName() + " se depara com uma situação inusitada:");
        Random random = new Random();
        int numeroHistoria = random.nextInt(3) + 1;

        switch (numeroHistoria) {
            case 1:
                JOptionPane.showMessageDialog(null, "Você tropeça e encontra um anel mágico!");
                Item anelRegenerativo = new Item("anelRegenerativo", 20, 1, "Vendível");
                
                player.getInventory().add(anelRegenerativo);
                player.equipItem(anelRegenerativo);
                JOptionPane.showMessageDialog(null, "Ao colocar o anel, "+ player.getName() +" não sentiu nenhuma difença!");
                //implementar regen de vida
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Distraído, você é atingido por uma maçã, causando 3 de dano.");
                player.setHealth(player.getHealth() - 3);
                Consumivel maca = new Consumivel("Maçã", 2, 1, 2);
                
                JOptionPane.showMessageDialog(null, "A maçã não causa muito dano, mas pode ser útil de alguma forma. " + player.getName() + " coletou Maçã!");
                player.getInventory().add(maca);
                
                break;
            case 3:
                JOptionPane.showMessageDialog(null, player.getName() + " encontra uma bolsa no chão, curioso, abre e encontrar alguns itens nele: ");
                Item item1 = new Item("Gema Preciosa", 50, 1, "Vendível");
                Consumivel item2 = new Consumivel("Planta Medicinal", 3, 5, 3);
                Item item3 = new Item("Bolsa", 8, 1, "Vendível");

                player.getInventory().add(item1);
                player.getInventory().add(item2);
                player.getInventory().add(item3);
                
                break;
        }
        StringBuilder mensagemItens = new StringBuilder("Você agora possui: \n");
        for (Item item : player.getInventory()) {
            mensagemItens.append(item.getName()).append("\n");
        }
        mensagemItens.setLength(mensagemItens.length() - 1);
        JOptionPane.showMessageDialog(null, mensagemItens.toString());
    }
    
}
