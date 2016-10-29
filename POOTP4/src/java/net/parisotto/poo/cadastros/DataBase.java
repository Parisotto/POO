package net.parisotto.poo.cadastros;
import java.util.ArrayList;
public class DataBase {
    private static ArrayList<Clientes> clientes = new ArrayList();
    private static ArrayList<Fornecedores> fornecedores = new ArrayList();
    private static int action;

    public static ArrayList<Clientes> getClientes() { return clientes; }
    public static ArrayList<Fornecedores> getFornecedores() { return fornecedores; }
    public static int getAction(){ return DataBase.action; }
    public static void setAction(int action){ DataBase.action = action; }
}
