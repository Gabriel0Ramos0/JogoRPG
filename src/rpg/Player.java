package rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int attack;
    private int defense;
    private int level;
    private int experience;
    private int coins;
    private int tempDefense;
    private int danoRecebidoExtra;
    private List<Item> inventory;
    private String playerClass;
    private static final int BASE_XP_PER_LEVEL = 10;
    private static final int XP_INCREASE_PER_LEVEL = 10;
    private Map<String, Boolean> partesHistoria;
    private boolean hasArmourAncestral = false;


    public Player(String name) {
    	if (name == null || name.trim().isEmpty()) {
            this.name = "Steve";
        } else {
            this.name = name;
        }
        this.health = 100;
        this.maxHealth = 100;
        this.attack = 5;
        this.defense = 10;
        this.tempDefense = defense;
        this.level = 1;
        this.experience = 0;
        this.inventory = new ArrayList<>();
        this.coins = 25;
        this.partesHistoria = new HashMap<>();
        this.danoRecebidoExtra = 0;
        this.ganhaEscudoAdicional();
    }
    
    private static int calculateXPPerLevel(int level) {
        return BASE_XP_PER_LEVEL + (level - 1) * XP_INCREASE_PER_LEVEL;
    }
    
    public void gainExperience(int amount) {
        experience += amount;
        JOptionPane.showMessageDialog(null, "Você ganhou " + amount + " de XP!");
        int xpRequiredForNextLevel = calculateXPPerLevel(level);
        if (experience >= xpRequiredForNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        int xpRequiredForNextLevel = calculateXPPerLevel(level);  
        while (experience >= xpRequiredForNextLevel) {
            level++;
            experience -= xpRequiredForNextLevel;
            maxHealth += 10;
            setHealth(maxHealth);
            setAttack(getAttack() + 2);
            setTempDefense(getTempDefense() + 2);
            setDefense(getDefense() + 2);
            JOptionPane.showMessageDialog(null, "Você subiu para o nível " + level + "!");
            JOptionPane.showMessageDialog(null, "Vida aumentou para " + maxHealth + ", Ataque aumentou para " + attack + ", Defesa aumentou para " + defense + ".");
            xpRequiredForNextLevel = calculateXPPerLevel(level);
        }
    }
    
    public void chooseClass(String className) {
        switch (className) {
            case "Escudeiro":
            	this.playerClass = className;
                activateEscudeiro();
                break;
            case "Lanceiro":
            	this.playerClass = className;
                activateLanceiro();
                break;
            case "Arqueiro":
            	this.playerClass = className;
            	activateAqueiro();
                break;
            case "Espadachim":
            	this.playerClass = className;
            	activateCavaleirodaEspada();
                break;
            default:
            	this.playerClass = className;
                initializeDefaultPlayer();
                break;
        }
    }
    
    public String getPlayerClass() {
        return playerClass;
    }

    private void activateEscudeiro() {
    	setHealth(getHealth()  + 10);
    	setMaxHealth(getMaxHealth() + 10);
        setAttack(getAttack() - 4);
        setDefense(getDefense() + 10);
        setTempDefense(getTempDefense() + 10);
        
        Equipavel shield = new Equipavel("Escudo", 15, 1, 0, 0, 0);
        Consumivel bread = new Consumivel("Pão", 2, 3, 10);
        Item cellphone = new Item("Celular", 15, 1, "Vendível");
        Item watch = new Item("Relógio", 12, 1, "Vendível");
        
        equipItem(shield);
        getInventory().add(bread);
        getInventory().add(cellphone);
        getInventory().add(watch);
    }
    private void activateLanceiro() {
    	setHealth(getHealth()  + 5);
    	setMaxHealth(getMaxHealth() + 5);
        setAttack(getAttack() + 6);
        setDefense(getDefense() - 10);
        setTempDefense(getTempDefense() - 10);
        
        Equipavel lance = new Equipavel("Lança", 15, 1, 0, 0, 0);
        Consumivel energyDrink = new Consumivel("Bebida Energética", 5, 3, 15);
        Item map = new Item("Mapa Antigo", 20, 1, "Vendível");

        equipItem(lance);
        getInventory().add(energyDrink);
        getInventory().add(map);
        
    }
    private void activateAqueiro() {
    	setHealth(getHealth()  - 20);
    	setMaxHealth(getMaxHealth() - 20);
        setAttack(getAttack() + 1);
        setDefense(getDefense() - 9);
        setTempDefense(getTempDefense() - 9);
        
        Equipavel armor = new Equipavel("Armadura de Fogo", 30, 1, 0, 0, 0);
        Consumivel spicyFood = new Consumivel("Prato Apimentado", 12, 1, 20);
        Consumivel pepper = new Consumivel("Pimenta", 3, 2, 8);
        
        equipItem(armor);
        getInventory().add(spicyFood);
        getInventory().add(pepper);        
    }
    private void activateCavaleirodaEspada() {
    	setHealth(getHealth()  - 50);
    	setMaxHealth(getMaxHealth() / 2);
        setAttack(getAttack() + 2);
        setDefense(getDefense() + 5);
        setTempDefense(getTempDefense() + 5);
        
        Equipavel sword = new Equipavel("Espada Longa", 50, 1, 0, 0, 0);
        Equipavel helmet = new Equipavel("Elmo Resistente", 25, 1, 0, 0, 0);
        Consumivel meatPie = new Consumivel("Torta de Carne", 15, 1, 35);
        Item notebook = new Item("Notebook", 75, 1, "Vendível");
        Item remoteControl = new Item("Controle Remoto", 52, 1, "Vendível");
        
        equipItem(sword);
        equipItem(helmet);
        getInventory().add(meatPie);
        getInventory().add(notebook);
        getInventory().add(remoteControl);
    }
    private void initializeDefaultPlayer() {
        setAttack(5);
        setDefense(10);
        setTempDefense(10);
    }
    
    public void decrementCoins(int amount) {
        if (amount >= 0 && coins >= amount) {
            coins -= amount;
        } else {
            System.out.println("Quantidade inválida de moedas ou saldo insuficiente.");
        }
    }
    
    public void incrementCoins(int amount) {
        if (amount >= 0) {
            coins += amount;
        } else {
            System.out.println("Quantidade inválida de moedas.");
        }
    }
    
    public void curaAnelRegenerativo() {
        for (Item item : inventory) {
            if (item.getName().equals("anelRegenerativo") && health < 100) {
                heal(1);
                JOptionPane.showMessageDialog(null, "O Anel Regenerativo curou 1 de vida!");
            }
        }
    }
    
    public boolean hasArmourAncestral() {
        return hasArmourAncestral;
    }
    
    void ganhaEscudoAdicional() {
        if (hasArmourAncestral) {
            setDefense(getDefense() + 1);
        }
    }
    
    public void regenerarEscudo() {
    	setDefense(tempDefense);
    }
    
    public int getTempDefense() {
		return tempDefense;
	}

	public void setTempDefense(int tempDefense) {
		this.tempDefense = tempDefense;
	}

	public boolean jaPassouPorParteDaHistoria(String parte) {
        return partesHistoria.getOrDefault(parte, false);
    }

    public void marcarPassagemPorParteDaHistoria(String parte) {
        partesHistoria.put(parte, true);
    }
    
    public int getDanoRecebidoExtra() {
        return danoRecebidoExtra;
    }

    public void setDanoRecebidoExtra(int danoRecebidoExtra) {
        this.danoRecebidoExtra = danoRecebidoExtra;
    }

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setHealth(int health) {
        this.health = health;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    
    public void setAttack(int attack) {
		this.attack = attack;
	}

	public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public int getCoins() {
        return coins;
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defense);
        health = Math.max(0, health - actualDamage);
    }

    public void heal(int amount) {
        health = Math.min(100, health + amount);
    }
    
    private int calculateTotalAttack() {
        int totalAttack = attack;
        for (Item item : inventory) {
            if (item instanceof Equipavel) {
                Equipavel equipavel = (Equipavel) item;
                totalAttack += equipavel.getDano();
            }
        }
        return totalAttack;
    }

    private int calculateTotalDefense() {
        int totalDefense = defense;
        for (Item item : inventory) {
            if (item instanceof Equipavel) {
                Equipavel equipavel = (Equipavel) item;
                totalDefense += equipavel.getDef();
            }
        }
        return totalDefense;
    }

    private int calculateTotalVida() {
        int totalVida = health;
        for (Item item : inventory) {
            if (item instanceof Equipavel) {
                Equipavel equipavel = (Equipavel) item;
                totalVida += equipavel.getVida();
            }
        }
        return totalVida;
    }

    public void updatePlayerStats() {
        setAttack(calculateTotalAttack());
        setDefense(calculateTotalDefense());
        setHealth(calculateTotalVida());
    }
    
    private void applyEquipavelStats(Equipavel equipavel) {
        attack += equipavel.getDano();
        defense += equipavel.getDef();
        health += equipavel.getVida();
        maxHealth += equipavel.getVida();
        updatePlayerStats();
    }

    public void equipItem(Item item) {
    	if (item instanceof Equipavel) {
            Equipavel equipavel = (Equipavel) item;
            applyEquipavelStats(equipavel);
            
            if ("armaduraAncestral".equals(item.getName())) {
                hasArmourAncestral = true;
            }            
            inventory.add(item);
            JOptionPane.showMessageDialog(null, "Você equipou: " + item.getName());
        } else {
        	inventory.add(item);
        }
    }

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public void setName(String name) {
			this.name = name;
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}
    
}
