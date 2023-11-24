package rpg;

class Item {
    private String name;
    private int value;
    private int quantity;

    public Item(String name, int value, int quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }
    
    public int getQuantity() {
        return quantity;
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
    
}