package dao;

import conexion.Conexion;
import modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {

    // CREATE
    public boolean registrarProducto(Producto producto) {

        String sql = "INSERT INTO productos (codigo, nombre, descripcion, precio_base, precio_venta, categoria, cantidad, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecioBase());
            ps.setDouble(5, producto.getPrecioVenta());
            ps.setString(6, producto.getCategoria());
            ps.setInt(7, producto.getCantidad());
            ps.setString(8, producto.getEstado());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar el producto: " + e.getMessage());
            return false;
        }
    }

    // READ
    public Producto buscarProducto(String codigo) {

        String sql = "SELECT * FROM productos WHERE codigo = ? AND estado = 'ACTIVO'";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, codigo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Producto producto = new Producto();

                producto.setCodigo(rs.getString("codigo"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioBase(rs.getDouble("precio_base"));
                producto.setPrecioVenta(rs.getDouble("precio_venta"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setEstado(rs.getString("estado"));

                return producto;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el producto: " + e.getMessage());
        }

        return null;
    }

    // UPDATE
    public boolean actualizarProducto(Producto producto) {

        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio_base = ?, precio_venta = ?, categoria = ?, cantidad = ?, estado = ? WHERE codigo = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecioBase());
            ps.setDouble(4, producto.getPrecioVenta());
            ps.setString(5, producto.getCategoria());
            ps.setInt(6, producto.getCantidad());
            ps.setString(7, producto.getEstado());
            ps.setString(8, producto.getCodigo());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
            return false;
        }
    }

    // DELETE (Eliminación lógica)
    public boolean eliminarProducto(String codigo) {

        String sql = "UPDATE productos SET estado = 'INACTIVO' WHERE codigo = ?";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, codigo);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
            return false;
        }
    }

}