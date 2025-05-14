import java.sql.*;
import java.util.*;

public class BankingSystem1 {
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "system";
    static final String PASS = "ora123";

    static Connection conn;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conn == null) {
                System.out.println("Database connection failed.");
                return;
            } else {
                System.out.println("Database connection established.");
            }
            int choice;
            do {
                System.out.println("\n--- Banking Management System ---");
                System.out.println("1.  Show Customer Records");
                System.out.println("2.  Add Customer Record");
                System.out.println("3.  Delete Customer Record");
                System.out.println("4.  Update Customer Record");
                System.out.println("5.  Open Branch");
                System.out.println("6.  Create Account");
                System.out.println("7.  Generate Loan");
                System.out.println("8.  Show Account Details of a Customer");
                System.out.println("9.  Show Loan Details of a Customer");
                System.out.println("10. Deposit Money to an Account");
                System.out.println("11. Withdraw Money from an Account");
                System.out.println("12. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:  showCustomers(); break;
                    case 2:  addCustomer(); break;
                    case 3:  deleteCustomer(); break;
                    case 4:  updateCustomer(); break;
                    case 5:  createBranch();break;
                    case 6:  createAccount();break;
                    case 7:  createLoan();break;
                    case 8:  showAccountDetails(); break;
                    case 9:  showLoanDetails(); break;
                    case 10: depositMoney(); break;
                    case 11: withdrawMoney(); break;
                    case 12: System.out.println("Exiting program."); break;
                    default: System.out.println("Invalid choice.");
                }
            } while (choice != 12);

        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    static void showCustomers() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM customer");

        while (rs.next()) {
            System.out.println("ID: " + rs.getString("cust_no").toUpperCase()
                    + ", Name: " + rs.getString("name")
                    + ", Phone: " + rs.getString("phoneno")
                    + ", City: " + rs.getString("city"));
        }
    }

    static void addCustomer() throws SQLException {
        System.out.print("Enter Customer ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Phone No: ");
        String phone = sc.nextLine();
        System.out.print("Enter City: ");
        String city = sc.nextLine();

        String sql = "INSERT INTO customer VALUES (?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, id);
        pst.setString(2, name);
        pst.setString(3, phone);
        pst.setString(4, city);

        int result = pst.executeUpdate();
        System.out.println(result > 0 ? "Customer added." : "Failed to add.");
    }

    static void deleteCustomer() throws SQLException {
        System.out.print("Enter Customer ID to delete: ");
        String id = sc.nextLine();

        PreparedStatement pst = conn.prepareStatement("DELETE FROM customer WHERE cust_no = ?");
        pst.setString(1, id);

        int result = pst.executeUpdate();
        System.out.println(result > 0 ? "Customer deleted." : "Customer not found.");
    }

    static void updateCustomer() throws SQLException {
        System.out.print("Enter Customer ID to update: ");
        String id = sc.nextLine();

        System.out.println("1. Update Name");
        System.out.println("2. Update Phone");
        System.out.println("3. Update City");
        int ch = sc.nextInt();
        sc.nextLine();

        String sql = "";
        switch (ch) {
            case 1:
                System.out.print("Enter new name: ");
                String name = sc.nextLine();
                sql = "UPDATE customer SET name = ? WHERE cust_no = ?";
                PreparedStatement pst1 = conn.prepareStatement(sql);
                pst1.setString(1, name);
                pst1.setString(2, id);
                System.out.println(pst1.executeUpdate() > 0 ? "Updated." : "Customer not found.");
                break;
            case 2:
                System.out.print("Enter new phone: ");
                String phone = sc.nextLine();
                sql = "UPDATE customer SET phoneno = ? WHERE cust_no = ?";
                PreparedStatement pst2 = conn.prepareStatement(sql);
                pst2.setString(1, phone);
                pst2.setString(2, id);
                System.out.println(pst2.executeUpdate() > 0 ? "Updated." : "Customer not found.");
                break;
            case 3:
                System.out.print("Enter new city: ");
                String city = sc.nextLine();
                sql = "UPDATE customer SET city = ? WHERE cust_no = ?";
                PreparedStatement pst3 = conn.prepareStatement(sql);
                pst3.setString(1, city);
                pst3.setString(2, id);
                System.out.println(pst3.executeUpdate() > 0 ? "Updated." : "Customer not found.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    static void createBranch() throws SQLException {
        System.out.print("Enter Branch Code: ");
        String code = sc.nextLine();
        System.out.print("Enter Branch Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Branch City: ");
        String city = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO branch VALUES (?, ?, ?)");
        ps.setString(1, code);
        ps.setString(2, name);
        ps.setString(3, city);
        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Branch added successfully." : "Failed to add branch.");
    }

    static void createAccount() throws SQLException {
        System.out.print("Enter Account No: ");
        String accNo = sc.nextLine();
        System.out.print("Enter Customer No: ");
        String custNo = sc.nextLine();
        System.out.print("Enter Account Type: ");
        String type = sc.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = Double.parseDouble(sc.nextLine());
        System.out.print("Enter Branch Code: ");
        String branchCode = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO account VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, accNo);
        ps.setString(2, custNo);
        ps.setString(3, type);
        ps.setDouble(4, balance);
        ps.setString(5, branchCode);

        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Account created successfully." : "Failed to create account.");
    }

    static void createLoan() throws SQLException {
        System.out.print("Enter Loan No: ");
        String loanNo = sc.nextLine().toUpperCase();
        System.out.print("Enter Customer No: ");
        String custNo = sc.nextLine();
        System.out.print("Enter Loan Amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        System.out.print("Enter Branch Code: ");
        String branchCode = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO loan VALUES (?, ?, ?, ?)");
        ps.setString(1, loanNo);
        ps.setString(2, custNo);
        ps.setDouble(3, amount);
        ps.setString(4, branchCode);

        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Loan generated successfully." : "Failed to generate loan.");
    }

    static void showAccountDetails() throws SQLException {
        System.out.print("Enter Customer No: ");
        String custNo = sc.nextLine();

        String query = """
                SELECT c.*, a.account_no, a.type, a.balance,
                       b.branch_code, b.branch_name, b.branch_city
                FROM customer c
                JOIN account a ON c.cust_no = a.cust_no
                JOIN branch b ON a.branch_code = b.branch_code
                WHERE c.cust_no = ?
                """;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, custNo);
        ResultSet rs = ps.executeQuery();

        boolean found = false;
        while (rs.next()) {
            found = true;
            System.out.printf("""
                    CUST_NO: %s | NAME: %s | PHONE: %s | CITY: %s
                    ACCOUNT_NO: %s | TYPE: %s | BALANCE: %.2f
                    BRANCH_CODE: %s | BRANCH_NAME: %s | BRANCH_CITY: %s
                    \n""",
                    rs.getString("cust_no"), rs.getString("name"), rs.getString("phoneno"), rs.getString("city"),
                    rs.getString("account_no"), rs.getString("type"), rs.getDouble("balance"),
                    rs.getString("branch_code"), rs.getString("branch_name"), rs.getString("branch_city"));
        }
        if (!found) System.out.println("No account found for this customer.");
    }

    static void showLoanDetails() throws SQLException {
        System.out.print("Enter Customer No: ");
        String custNo = sc.nextLine();

        String query = """
                SELECT c.*, l.loan_no, l.amount,
                       b.branch_code, b.branch_name, b.branch_city
                FROM customer c
                JOIN loan l ON c.cust_no = l.cust_no
                JOIN branch b ON l.branch_code = b.branch_code
                WHERE c.cust_no = ?
                """;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, custNo);
        ResultSet rs = ps.executeQuery();

        boolean found = false;
        while (rs.next()) {
            found = true;
            System.out.printf("""
                    CUST_NO: %s | NAME: %s | PHONE: %s | CITY: %s
                    LOAN_NO: %s | AMOUNT: %.2f
                    BRANCH_CODE: %s | BRANCH_NAME: %s | BRANCH_CITY: %s
                    \n""",
                    rs.getString("cust_no"), rs.getString("name"), rs.getString("phoneno"), rs.getString("city"),
                    rs.getString("loan_no"), rs.getDouble("amount"),
                    rs.getString("branch_code"), rs.getString("branch_name"), rs.getString("branch_city"));
        }
        if (!found) System.out.println("No loan found for this customer.");
    }

    static void depositMoney() throws SQLException {
        System.out.print("Enter Account No: ");
        String acc = sc.nextLine();
        System.out.print("Enter amount to deposit: ");
        double amt = sc.nextDouble();
        sc.nextLine();

        PreparedStatement pst = conn.prepareStatement("UPDATE account SET balance = balance + ? WHERE account_no = ?");
        pst.setDouble(1, amt);
        pst.setString(2, acc);

        System.out.println(pst.executeUpdate() > 0 ? "Deposited successfully." : "Account not found.");
    }

    static void withdrawMoney() throws SQLException {
        System.out.print("Enter Account No: ");
        String acc = sc.nextLine();
        System.out.print("Enter amount to withdraw: ");
        double amt = sc.nextDouble();
        sc.nextLine();

        PreparedStatement check = conn.prepareStatement("SELECT balance FROM account WHERE account_no = ?");
        check.setString(1, acc);
        ResultSet rs = check.executeQuery();

        if (rs.next()) {
            double balance = rs.getDouble("balance");
            if (balance >= amt) {
                PreparedStatement pst = conn.prepareStatement("UPDATE account SET balance = balance - ? WHERE account_no = ?");
                pst.setDouble(1, amt);
                pst.setString(2, acc);
                pst.executeUpdate();
                System.out.println("Withdrawn successfully.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
}