package rpg;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Eventos {

	public static void comprarItens(Player player) {
	    JOptionPane.showMessageDialog(null, "Você encontra um comerciante amigável que está disposto a vender itens para você.");
	    JOptionPane.showMessageDialog(null, "(Poções são armazenadas separadamente)");

	    // Pensar em uma maneira de deixar 20 itens aqui e mostrar 5 aleatórios para comprar
	        Consumivel itemAVenda1 = new Consumivel("Poção de Cura", 15, 5, 25);
	        Consumivel itemAVenda2 = new Consumivel("Poção de Mana", 15, 5, 25);
	        Item itemAVenda3 = new Item("Minério de Ametista", 32, 2, "Vendível");
	        Equipavel itemAVenda4 = new Equipavel("Espada Misteriosa", 47, 1, 4, 0, 0);
	        Equipavel itemAVenda5 = new Equipavel("Botas para neve (com cristal)", 75, 1, 0, 15, 5);

	        List<Item> itensDisponiveis = new ArrayList<>();
	        itensDisponiveis.add(itemAVenda1);
	        itensDisponiveis.add(itemAVenda2);
	        itensDisponiveis.add(itemAVenda3);
	        itensDisponiveis.add(itemAVenda4);
	        itensDisponiveis.add(itemAVenda5);
	    do {
	        StringBuilder escolhaItens = new StringBuilder("Escolha os itens que deseja comprar:\n");
	        escolhaItens.append("Ouro disponível: ").append(player.getCoins()).append("\n");

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
	                if (itemEscolhido instanceof Equipavel) {
	                    player.equipItem(itemEscolhido);
	                } else {
	                    player.getInventory().add(itemEscolhido);
	                    if (itemEscolhido.getQuantity() > 1) {
	                        itemEscolhido.decrementQuantity();
	                    } else {
	                        itensDisponiveis.remove(itemEscolhido);
	                    }
	                    quantidadePorItem.put(itemEscolhido.getName(), quantidadePorItem.getOrDefault(itemEscolhido.getName(), 0) + 1);
	                }
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
	                mensagemCompra.setLength(mensagemCompra.length() - 2);
	            }
	            JOptionPane.showMessageDialog(null, mensagemCompra.toString() + "!");
	        } else {
	            JOptionPane.showMessageDialog(null, "Você não possui moedas suficientes para comprar esses itens.");
	            break;
	        }

	        int opcaoContinuar = JOptionPane.showConfirmDialog(null, "Deseja comprar mais itens?", "Continuar Compras", JOptionPane.YES_NO_OPTION);
	        if (opcaoContinuar != JOptionPane.YES_OPTION) {
	            break;
	        }
	    } while (true);
	}

	public static void venderItens(Player player) {
	    JOptionPane.showMessageDialog(null, "Você encontra um mercador disposto a comprar seus itens.");
	    do {
	        List<Item> itensParaVender = player.getInventory();
	        StringBuilder escolhaItens = new StringBuilder("Escolha os itens que deseja vender:\n");
	        escolhaItens.append("Ouro disponível: ").append(player.getCoins()).append("\n");

	        for (int i = 0; i < itensParaVender.size(); i++) {
	            Item item = itensParaVender.get(i);
	            escolhaItens.append(i + 1).append(". ").append(item.getName())
	                        .append(" - Valor: ").append(item.getValue())
	                        .append(" - Quantidade: ").append(item.getQuantity()).append("\n");
	        }
	        int[] indicesEscolhidos = obterIndicesItens(escolhaItens.toString(), itensParaVender.size());
	        Map<String, Integer> quantidadePorItem = new HashMap<>();
	        List<Item> itensVendidos = new ArrayList<>();

	        // Remover itens da lista, percorrendo de trás para frente
	        for (int i = indicesEscolhidos.length - 1; i >= 0; i--) {
	            int indice = indicesEscolhidos[i];
	            if (indice >= 0 && indice < itensParaVender.size()) {
	                Item itemVendido = itensParaVender.get(indice);
	                player.incrementCoins(itemVendido.getValue());
	                itemVendido.decrementQuantity();
	                itensVendidos.add(itemVendido);
	                quantidadePorItem.put(itemVendido.getName(), quantidadePorItem.getOrDefault(itemVendido.getName(), 0) + 1);

	                // Remover o item do inventário se a quantidade for zero
	                if (itemVendido.getQuantity() == 0) {
	                    itensParaVender.remove(itemVendido);
	                }
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

	        int opcaoContinuar = JOptionPane.showConfirmDialog(null, "Deseja vender mais itens?", "Continuar Vendas", JOptionPane.YES_NO_OPTION);
	        if (opcaoContinuar != JOptionPane.YES_OPTION) {
	            break;
	        }

	    } while (true);
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

    private static int calcularCustoTotal(List<Item> itens, int[] indicesEscolhidos) {
        int custoTotal = 0;
        for (int indice : indicesEscolhidos) {
            custoTotal += itens.get(indice).getValue();
        }
        return custoTotal;
    }

    private static int calcularCustoTotal(List<Item> itens) {
        int custoTotal = 0;
        for (Item item : itens) {
            custoTotal += item.getValue();
        }
        return custoTotal;
    }

    private static List<Item> obterItensPorIndices(List<Item> itens, int[] indicesEscolhidos) {
        List<Item> itensEscolhidos = new ArrayList<>();
        for (int indice : indicesEscolhidos) {
            itensEscolhidos.add(itens.get(indice));
        }
        return itensEscolhidos;
    }
    
    private static Item escolherItemAleatorio(List<Item> inventory) {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(inventory.size());
        return inventory.get(indiceAleatorio);
    }
    
    // Método para simular a coleta de itens aleatórios com histórias e efeitos
    public static void coletarItensAleatoriosComHistorias(Player player) {
        JOptionPane.showMessageDialog(null, "Enquanto explora a região, " + player.getName() + " se depara com uma situação inusitada:");
        Random random = new Random();
        int numeroHistoria = random.nextInt(6) + 1;

        switch (numeroHistoria) {
            case 1:
                JOptionPane.showMessageDialog(null, "Você tropeça e encontra um anel mágico!");
                Item anelRegenerativo = new Item("anelRegenerativo", 20, 1, "Vendível");
                player.equipItem(anelRegenerativo);
                JOptionPane.showMessageDialog(null, "Ao colocar o anel, "+ player.getName() +" não sentiu nenhuma difença!");
                
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
            case 4:
                JOptionPane.showMessageDialog(null, player.getName() + " pensa que as plantas em volta podem ser úteis mais a frente, então decide cata-lás.");
                Item item4 = new Item("Folha", 1, 12, "Vendível");
                Consumivel item5 = new Consumivel("Planta Medicinal", 3, 2, 3);
                Item item6 = new Item("Folha de télia", 19, 1, "Vendível");

                player.getInventory().add(item4);
                player.getInventory().add(item5);
                player.getInventory().add(item6);
                
                break;
            case 5:
            	JOptionPane.showMessageDialog(null, player.getName() + " encontra uma poção estranha em uma encruzilhada."
                        + "\nA poção emite um brilho peculiar e exala um aroma misterioso.");

                String[] opcoesPocao = {"Beber a poção", "Ignorar a poção"};
                int escolhaPocao = JOptionPane.showOptionDialog(null, "O que você deseja fazer?", "Poção Estranha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesPocao, opcoesPocao[0]);

                switch (escolhaPocao) {
                    case 0:
                    	JOptionPane.showMessageDialog(null, player.getName() + " decide beber a poção. Uma sensação de calor percorre seu corpo.");
                        JOptionPane.showMessageDialog(null, "No entanto, algo não está certo. A sensação de calor transforma-se em uma dor intensa, como se sua essência estivesse sendo perturbada.");
                        player.takeDamage(50);
                        JOptionPane.showMessageDialog(null, "Após momentos de agonia insuportável, a dor diminui gradualmente, deixando " + player.getName() + " debilitado e exausto.");
                        JOptionPane.showMessageDialog(null, player.getName() +" perde 50 de vida!");
                        break;

                    case 1:
                        JOptionPane.showMessageDialog(null, player.getName() + " opta por ignorar a poção, com receio de seus efeitos desconhecidos.");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. " + player.getName() + " fica indeciso sobre como lidar com a poção estranha.");
                }
                break;
            case 6:
                JOptionPane.showMessageDialog(null, player.getName() + " encontra um objeto misterioso em seu caminho."
                        + "\nO objeto emana uma aura sombria, mas parece poderoso.");

                String[] opcoesItem = {"Equipar o objeto", "Ignorar o objeto"};
                int escolhaItem = JOptionPane.showOptionDialog(null, "O que você deseja fazer?", "Objeto Misterioso", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesItem, opcoesItem[0]);

                switch (escolhaItem) {
                    case 0:
                        JOptionPane.showMessageDialog(null, player.getName() + " decide equipar o objeto. Uma sensação sombria envolve " + player.getName() + ".");
                        
                        player.setDanoRecebidoExtra(player.getDanoRecebidoExtra() + 3);

                        JOptionPane.showMessageDialog(null, "O objeto misterioso revela sua verdadeira natureza. " + player.getName() + " agora carrega uma maldição impossível de ser removida! "
                        		+ "tornando-o mais vulnerável em batalhas."
                        		+ player.getName() + " agora recebe mais 3 de danos dos monstros!");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, player.getName() + " opta por ignorar o objeto, temendo seus efeitos sombrios.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. " + player.getName() + " fica indeciso sobre como lidar com o objeto misterioso.");
                }
                break;
                
            default:
                throw new IllegalStateException("Número do evento inválido: " + numeroHistoria);
        }
        StringBuilder mensagemItens = new StringBuilder("Você agora possui: \n");
        for (Item item : player.getInventory()) {
            mensagemItens.append(item.getName()).append("\n");
        }
        mensagemItens.setLength(mensagemItens.length() - 1);
        JOptionPane.showMessageDialog(null, mensagemItens.toString());
    }
    
    public Monstro bossMercador() {
        return Monstro.Mercador();
    }
    
    public Monstro bossDragao() {
        return Monstro.DragaoNegro();
    }
    
    public Monstro bossFinal() {
        return Monstro.Sombras();
    }
    
    public Monstro escolherMonstroAleatorioEpic() {
        Random random = new Random();
        int numeroMonstro = random.nextInt(3);

        switch (numeroMonstro) {
            case 0:
                return Monstro.Esqueleto();
            case 1:
                return Monstro.Ogro();
            case 2:
            	return Monstro.DragaoDeGelo();
            default:
                throw new IllegalStateException("Número de monstro inválido: " + numeroMonstro);
        }
    }
    
    public Monstro escolherMonstroAleatorioComum() {
        Random random = new Random();
        int numeroMonstro = random.nextInt(4);

        switch (numeroMonstro) {
            case 0:
                return Monstro.besta();
            case 1:
                return Monstro.Goblin();
            case 2:
                return Monstro.Cão();
            case 3:
                return Monstro.Lobo();
            default:
                throw new IllegalStateException("Número de monstro inválido: " + numeroMonstro);
        }
    }
}
