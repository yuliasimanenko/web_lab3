package ru.ifmo.web_lab3.Beans;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import ru.ifmo.web_lab3.Entities.HistoryEntry;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HistoryBean {
    static final String DB_URL = "jdbc:postgresql://pg/studs";

    private static SessionFactory factory;



    private String userName = "s242577";
    private String userPassword = "";
    private boolean areCredentialsEntered = false;
    private boolean areCredentialsValid = false;

    private Connection connection = null;
    private Statement statement = null;

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean getAreCredentialsEntered() {
        return areCredentialsEntered;
    }

    public boolean getAreCredentialsValid() {
        return areCredentialsValid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void submitCredentials() throws Exception{
        areCredentialsEntered = userName != null && userPassword != null && !userPassword.equals("");
        areCredentialsValid = areCredentialsEntered && initConnection();
    }

    private boolean initConnection(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            return false;
        }
        try {
            //connection = DriverManager.getConnection(DB_URL, userName, userPassword);
            //statement = connection.createStatement();
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean createTable(){
        if (connection == null || statement == null)
            return false;
        final String create = "CREATE TABLE IF NOT EXISTS WEB_LAB3_HISTORY (" +
                "ID SERIAL PRIMARY KEY, " +
                "X DOUBLE PRECISION NOT NULL, " +
                "Y DOUBLE PRECISION NOT NULL, " +
                "R DOUBLE PRECISION NOT NULL, " +
                "RES BOOLEAN NOT NULL" +
                ");";
        try {
            statement.execute(create);
        }
        catch (SQLException e){

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addResult(double x, double y, double r, boolean res){
        if (factory == null)
            if (!initConnection())
                return false;

        //if (!createTable())
        //    return false;
//        try {
//            factory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            return false;
//        }
        Session session = factory.openSession();
        Transaction transaction = null;
        Long ID = null;
        try{
            transaction = session.beginTransaction();
            Points points= new Points(x,y,r,res);
            session.persist(points);
            session.flush();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction!=null)
                transaction.rollback();
            return false;
        }finally {
            session.close();
        }

//        String add = "INSERT INTO WEB_LAB3_HISTORY (X, Y, R, RES) VALUES(" + x + ", "+ y
//                + ", " + r + ", " + res + ");";
//        try {
//            statement.execute(add);
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//            return false;
//        }



        return true;
    }

    public Points[] getEntries(){
        if (factory == null)
            if (!initConnection())
                return new Points[0];

        Session session = factory.openSession();
        //List<Points> points = session.createQuery("SELECT ID, X, Y, R, RES FROM WEB_LAB3_HISTORY", Points.class).list();
        List<Points> points = session.createQuery("from Points order by ID desc").list();
//        Query query = session.createNativeQuery("SELECT * from WEB_LAB3_HISTORY;");
//        query.setResultTransformer(Transformers.aliasToBean(Points.class));
//        List<Points> points = query.getResultList();
        Points[] pointsArr = new Points[points.size()];
        pointsArr = points.toArray(pointsArr);
        session.close();
        return pointsArr;

//        if (connection == null || statement == null)
//            if (!initConnection())
//                return new HistoryEntry[0];
//
//        String get = "SELECT * FROM WEB_LAB3_HISTORY ORDER BY ID DESC;";
//        List<HistoryEntry> entries = new ArrayList<>();
//
//        try {
//            ResultSet result = statement.executeQuery(get);
//            while (result.next()) {
//                entries.add(new HistoryEntry(
//                        result.getDouble("x"),
//                        result.getDouble("y"),
//                        result.getDouble("r"),
//                        result.getBoolean("res")));
//            }
//
//            HistoryEntry[] entriesArr = new HistoryEntry[entries.size()];
//            entriesArr = entries.toArray(entriesArr);
//            return entriesArr;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            return new HistoryEntry[0];
//        }
    }

    public void clear()  {
        if (factory == null)
            if (!initConnection())
                return;

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Points").executeUpdate();
        transaction.commit();
        session.close();

//        if (connection == null || statement == null)
//            if (!initConnection())
//                return;
//
//        String clear = "DELETE FROM WEB_LAB3_HISTORY;";
//
//        try {
//            statement.execute(clear);
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
    }

}
