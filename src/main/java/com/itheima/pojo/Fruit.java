package com.itheima.pojo;

public class Fruit {
    private Integer id;
    private String fruitName;
    private Integer fruitQuantity;
    private String originalPlace;
    private String supplier;

    public Fruit(Integer id, String fruitName, Integer fruitQuantity, String originalPlace, String supplier) {
        this.id = id;
        this.fruitName = fruitName;
        this.fruitQuantity = fruitQuantity;
        this.originalPlace = originalPlace;
        this.supplier = supplier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public Integer getFruitQuantity() {
        return fruitQuantity;
    }

    public void setFruitQuantity(Integer fruitQuantity) {
        this.fruitQuantity = fruitQuantity;
    }

    public String getOriginalPlace() {
        return originalPlace;
    }

    public void setOriginalPlace(String originalPlace) {
        this.originalPlace = originalPlace;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", fruitName='" + fruitName + '\'' +
                ", fruitQuantity=" + fruitQuantity +
                ", originalPlace='" + originalPlace + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}
