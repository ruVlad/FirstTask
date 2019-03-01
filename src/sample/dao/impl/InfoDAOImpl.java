package sample.dao.impl;

import sample.dao.InfoDAO;
import sample.entity.Info;
import sample.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InfoDAOImpl implements InfoDAO{

    private Connection conn;


    public InfoDAOImpl(String url,String user,String password) {
        conn = DBUtil.getConnection(url, user, password);
    }

    @Override
    public void insert(Info info) {
        try {
            String query = "insert into info (date, symbolrus, symboleng, number, fractional) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, info.getDate());
            preparedStatement.setString(2, info.getSymbolRus());
            preparedStatement.setString(3, info.getSymbolEng());
            preparedStatement.setInt(4, info.getNumber());
            preparedStatement.setDouble(5, info.getFractional());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "delete from info where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Info info) {
        try {
            String query = "update info set date=?, symbolrus=?, symboleng=?, number=?, fractional=? where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, info.getDate());
            preparedStatement.setString(2, info.getSymbolRus());
            preparedStatement.setString(3, info.getSymbolEng());
            preparedStatement.setInt(4, info.getNumber());
            preparedStatement.setDouble(5, info.getFractional());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Info> getAll() {
        List<Info> infos = new ArrayList<Info>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from info");
            while (resultSet.next()) {
                Info info= new Info();
                info.setId(resultSet.getInt("id"));
                info.setDate(resultSet.getString("date"));
                info.setSymbolRus(resultSet.getString("symbolrus"));
                info.setSymbolRus(resultSet.getString("symboleng"));
                info.setNumber(resultSet.getInt("number"));
                info.setFractional(resultSet.getDouble("fractional"));
                infos.add(info);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infos;
    }

    @Override
    public Info getById(int id) {
        Info info= new Info();
        try {
            String query = "select * from info where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                info.setId(resultSet.getInt("id"));
                info.setDate(resultSet.getString("date"));
                info.setSymbolRus(resultSet.getString("symbolrus"));
                info.setSymbolRus(resultSet.getString("symboleng"));
                info.setNumber(resultSet.getInt("number"));
                info.setFractional(resultSet.getDouble("fractional"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

}