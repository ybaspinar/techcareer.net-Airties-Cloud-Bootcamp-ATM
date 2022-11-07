package com.ybaspinar.airtiesgraduationproject;
import java.util.Scanner;

import static com.ybaspinar.airtiesgraduationproject.Logger.createLogFile;
import static com.ybaspinar.airtiesgraduationproject.Logger.logToFile;
import static com.ybaspinar.airtiesgraduationproject.Customer.newCustomer;

// kasa: 1000 TL
// Switch case
// static
// method kullanın
// NOT: her bir işlem dosyaya yazılsın(Loglama)

// Login: username:admin@gmail.com password:root
// kullanıcı sistemdeki bilgileri dorğu girmediği süre zarfında 3 hakkından azaltarak
// 3 yanlış girişte hesap bloke olsun(System.exit(0))

// Customer class oluşturalım ( username,password,image, para )
//Eğer sistemdeki bilgileri klavyeden doğru girersem aşağıdaki seçenerkler çıksın yoksa çıkmasın

// 0-) para görüntüle
// 1-) Para yatıracağız (eğer paramız 0 ise para eklememiz zorlasın)
// 2-) Para çekeceğim
// 3-) Havale yapacağım  (Fake Mail() göndersin)
// 4-) EFT yapabileceğim (Fake Mail() göndersin)
// 5-) Fake Mail()
// 6-) Çıkış

// inheritance
// abstract
// interface
// enum
// static
// String metotları
// StringBuilder
// StringTokenizer
// Math
// Class üzerinden
// Diziler
// collection (List,Set,Map)
public class Atm {
    private static Customer customer = Customer.newCustomer();
    public static void login() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Kullanıcı adı:");
            String username = scan.nextLine();
            System.out.println("Şifre:");
            String password = scan.nextLine();
            if (authentication(username, password)) {
                break;
            }
            if (i == 2) {
                logToFile("Kullanıcı 3 kere yanlış giriş yaptığı için sistemden atıldı!");
                System.out.println("3 kere yanlış giriş yapıldığı için sistemden atıldınız!");
                exit();
            }
            System.out.println("Kullanıcı adı veya şifre yanlış!" + (2 - i) + " hakkınız kaldı!");
        }
    }

    public static boolean authentication(String username, String password) {
        return username.equals(customer.getUsername()) && password.equals(customer.getPassword());
    }
    public static void showMoney() {
        System.out.println("Bakiyeniz: " + customer.getMoney());
        logToFile("Bakiye görüntülendi!");
    }
    public static void depositMoney() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Yatırmak istediğiniz tutarı giriniz:");
        int money = scan.nextInt();
        if (money <= 0) {
        System.out.println("Lütfen 0'dan büyük bir değer giriniz!");
        logToFile("Kullanıcı 0'dan küçük bir değer girdi!");
            return;
        }
        customer.updateBalance(customer.getMoney() + money);
        customer.setMoney(customer.getMoney() + money);
        logToFile("Para yatırma işlemi başarılı!");
        logToFile("Yeni bakiye: " + customer.getMoney());
        System.out.println("Yeni bakiyeniz: " + customer.getMoney());
        fakeMail();

    }
    public static void withdrawMoney() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Çekmek istediğiniz tutarı giriniz:");
        int money = scan.nextInt();
        if (money <= 0) {
            System.out.println("Lütfen 0'dan büyük bir değer giriniz!");
            logToFile("Kullanıcı 0'dan küçük bir değer girdi!");
            return;
        }
        if (money > customer.getMoney()) {
            System.out.println("Bakiyeniz yetersiz!");
            logToFile("Kullanıcı bakiye yetersiz olduğu için para çekme başarısız!");
            return;
        }
        customer.updateBalance(customer.getMoney() - money);
        customer.setMoney(customer.getMoney() - money);
        System.out.println("Yeni bakiyeniz: " + customer.getMoney());
        logToFile("Para çekme işlemi başarılı!");
        logToFile("Yeni bakiye: " + customer.getMoney());
        fakeMail();
    }
    public static void transferMoney() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Havale yapmak istediğiniz tutarı giriniz:");
        int money = scan.nextInt();
        if (money <= 0) {
            System.out.println("Lütfen 0'dan büyük bir değer giriniz!");
            logToFile("Kullanıcı 0'dan küçük bir değer girdi!");
            return;
        }
        if (money > customer.getMoney()) {
            System.out.println("Bakiyeniz yetersiz!");
            logToFile("Havale yapmak istediğiniz tutar bakiyenizden fazla olduğu için işlem iptal edildi!");
            return;
        }
        customer.updateBalance(customer.getMoney() - money);
        customer.setMoney(customer.getMoney() - money);
        System.out.println("Havale işlemi başarıyla gerçekleştirildi!");
        System.out.println("Yeni bakiyeniz: " + customer.getMoney());
        logToFile("Havale işlemi başarıyla gerçekleştirildi!");
        logToFile("Yeni bakiye: " + customer.getMoney());
        fakeMail();
    }
    public static void eftMoney() {
        Scanner scan = new Scanner(System.in);
        System.out.println("EFT yapmak istediğiniz tutarı giriniz:");
        int money = scan.nextInt();
        if (money <= 0) {
            System.out.println("Lütfen 0'dan büyük bir değer giriniz!");
            logToFile("Kullanıcı 0'dan küçük bir değer girdi!");
            return;
        }
        if (money > customer.getMoney()) {
            System.out.println("Bakiyeniz yetersiz!");
            logToFile("Kullanıcı bakiyesi yetersiz olduğu için EFT işlemi gerçekleştirilemedi!");
            return;
        }
        customer.updateBalance(customer.getMoney() - money);
        customer.setMoney(customer.getMoney() - money);
        logToFile("EFT işlemi başarıyla gerçekleştirildi!");
        logToFile("Yeni bakiye: " + customer.getMoney());
        System.out.println("Yeni bakiyeniz: " + customer.getMoney());
        System.out.println("EFT işlemi başarıyla gerçekleştirildi!");
        fakeMail();
    }
    public static void fakeMail() {
        System.out.println("Mail gönderildi");
        logToFile("Mail gönderildi");
    }
    public static void exit() {
        logToFile("Sistem kapatılıyor");
        System.exit(0);
    }
    public static void menu() {
        System.out.println("Yapmak istediğiniz işlemi seçiniz:");
        System.out.println("0-) Para görüntüle");
        System.out.println("1-) Para yatır");
        System.out.println("2-) Para çek");
        System.out.println("3-) Havale");
        System.out.println("4-) EFT");
        System.out.println("5-) Mail");
        System.out.println("6-) Çıkış");
    }

    public static void main(String[] args) {
        createLogFile();
        Scanner scan = new Scanner(System.in);
        logToFile("Atm Başlatılıyor!");
        System.out.println("Giriş yapınız:");
        logToFile("Giriş yapılıyor!");
        login();
        logToFile("Giriş başarılı!");

        while (true) {
            menu();
            int choice = scan.nextInt();
            if (choice < 0 || choice > 6) {
                System.out.println("Geçersiz işlem!");
            } else  {
                switch (choice) {
                    case 0 -> showMoney();
                    case 1 -> depositMoney();
                    case 2 -> withdrawMoney();
                    case 3 -> transferMoney();
                    case 4 -> eftMoney();
                    case 5 -> fakeMail();
                    case 6 -> exit();
                }
            }

        }
    }
}
