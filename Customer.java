package com.ybaspinar.airtiesgraduationproject;

import java.io.*;

public class Customer {
    private String username;
    private String password;
    private String image;
    private int money;

    private Customer(String username, String password, String image, int money) {
        this.username = username;
        this.password = password;
        this.image = image;
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public static Customer newCustomer() {
        return new Customer("admin@gmail.com", "root", "image", readBalance());
    }

    public static void createCustomerFile() {
        File file = new File("./customers/customer.txt");
        File folder = new File("./customers/");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateBalance(int newBalance) {
        File file = new File("./customers/customer.txt");
        FileWriter writer = null;

        try {
            writer = new FileWriter(file);
            writer.write(String.valueOf(newBalance));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int readBalance() {
        File file = new File("./customers/customer.txt");
        BufferedReader reader = null;
        if (!file.exists()) {
            createCustomerFile();
            updateBalance(1000);
            return 1000;
        }
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String balance = null;
        try {
            balance = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Integer.parseInt(balance);
    }


}
