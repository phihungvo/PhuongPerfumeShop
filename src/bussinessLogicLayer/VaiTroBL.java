package bussinessLogicLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataTransferObject.VaiTro;

public class VaiTroBL {

	public static int themVaiTro(VaiTro vaiTro) throws SQLException {
		int lastInsertID = 0;

		try (Connection conn = CSDL.getKetNoi()) {
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO vaitro VALUES (null,'" + vaiTro.getTenVaiTro() + "','" + vaiTro.getMoTa() + "')";

			statement.executeUpdate(sql);
			ResultSet resultSet = statement.executeQuery("SELECT id FROM vaitro ORDER BY id DESC LIMIT 0,1");

			while (resultSet.next()) {
				lastInsertID = resultSet.getInt("id");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastInsertID;
	}

	public static List<VaiTro> dsVaiTro() throws SQLException {
		List<VaiTro> lsVaiTro = new ArrayList<VaiTro>();

		try (Connection conn = CSDL.getKetNoi()) {
			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM vaitro";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				VaiTro vaiTro = new VaiTro();
				vaiTro.setId(resultSet.getInt("id"));
				vaiTro.setTenVaiTro(resultSet.getString("tenvaitro"));
				vaiTro.setMoTa(resultSet.getString("mota"));
				lsVaiTro.add(vaiTro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsVaiTro;
	}

	public static void capNhatVaiTro(VaiTro vaiTro) throws SQLException {
		try (Connection conn = CSDL.getKetNoi()) {
			String sql = "UPDATE vaitro SET tenvaitro = '" + vaiTro.getTenVaiTro() + "', mota = '" + vaiTro.getMoTa()
					+ "' WHERE id = '" + vaiTro.getId() + "'";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}
}
