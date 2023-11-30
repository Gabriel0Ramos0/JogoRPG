package rpg;

public class Item {
    private String name;
    private int value;
    protected int quantity;
    private String category;

    public Item(String name, int value, int quantity, String category) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.category = category;
    }
    
    //Itens comprados "Consumíveis" ficam infinitos ao usar. Corrigir
    
    public int getQuantity() {
        return quantity;
    }
    
    public void incrementQuantity() {
        if (quantity >= 0) {
            quantity++;
        }
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
    
}