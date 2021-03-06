package model.send.receive;

import model.others.SpecialProperty;

import java.util.ArrayList;

public class ProductInfo {
    private final ArrayList<String> sellersNames;
    private final ArrayList<ProductSeller> productSellers;
    private int seenTime;
    private String id;
    private String name;
    private String status;
    private String mainCategory;
    private String subCategory;
    private ArrayList<SpecialProperty> specialProperties;
    private String description;
    private double scoreAverage;
    private boolean isItFile;
    private byte[] file;
    private String fileExtension;

    public ProductInfo() {
        sellersNames = new ArrayList<>();
        specialProperties = new ArrayList<>();
        productSellers = new ArrayList<>();
    }

    public boolean isItFile() {
        return isItFile;
    }

    public void setItFile(boolean itFile) {
        isItFile = itFile;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public int getSeenTime() {
        return seenTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice(String sellerUsername) {
        for (ProductSeller productSeller : productSellers) {
            if (productSeller.sellerName.equals(sellerUsername)) {
                return productSeller.price;
            }
        }
        return -1;
    }

    public double getFinalPrice(String sellerUsername) {
        for (ProductSeller productSeller : productSellers) {
            if (productSeller.sellerName.equals(sellerUsername)) {
                return productSeller.priceWithOff;
            }
        }
        return -1;
    }

    public void addProductSeller(String sellerUsername, double price, double priceWithOff, int numberInStock) {
        productSellers.add(new ProductSeller(sellerUsername, price, priceWithOff, numberInStock));
    }


    public ArrayList<String> getSellersNames() {
        ArrayList<String> sellerNames = new ArrayList<>();
        for (ProductSeller productSeller : productSellers) {
            sellerNames.add(productSeller.sellerName);
        }
        return sellerNames;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public ArrayList<SpecialProperty> getSpecialProperties() {
        return specialProperties;
    }

    public void setSpecialProperties(ArrayList<SpecialProperty> specialProperties) {
        this.specialProperties = specialProperties;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getScoreAverage() {
        return scoreAverage;
    }

    public void setScoreAverage(double scoreAverage) {
        this.scoreAverage = scoreAverage;
    }

    public int getNumberInStock(String seller) {
        for (ProductSeller productSeller : productSellers) {
            if (productSeller.sellerName.equalsIgnoreCase(seller)) return productSeller.numberInStock;
        }
        return 0;
    }

    public int getNumberInStock() {
        int number = 0;
        for (ProductSeller productSeller : productSellers) {
            number += productSeller.numberInStock;
        }
        return number;
    }

    public double getMinPrice() {
        double price = Double.MAX_VALUE;
        for (ProductSeller productSeller : productSellers) {
            if (productSeller.priceWithOff < price) price = productSeller.priceWithOff;
        }
        return price;
    }

    public double getMinPriceWithoutOff() {
        double price = Double.MAX_VALUE;
        for (ProductSeller productSeller : productSellers) {
            if (productSeller.price < price) price = productSeller.price;
        }
        return price;
    }

    public boolean isItInOff() {
        for (ProductSeller productSeller : productSellers) {
            if (productSeller.price != productSeller.priceWithOff && productSeller.priceWithOff != 0.0) return true;
        }
        return false;
    }


    private static class ProductSeller {
        private String sellerName;
        private double price;
        private double priceWithOff;
        private int numberInStock;


        public ProductSeller(String sellerUsername, double price, double priceWithOff, int numberInStock) {
            this.sellerName = sellerUsername;
            this.price = price;
            this.priceWithOff = priceWithOff;
            this.numberInStock = numberInStock;
        }

        public int getNumberInStock() {
            return numberInStock;
        }

        public void setNumberInStock(int numberInStock) {
            this.numberInStock = numberInStock;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getPriceWithOff() {
            return priceWithOff;
        }

        public void setPriceWithOff(double priceWithOff) {
            this.priceWithOff = priceWithOff;
        }
    }
}
