package principal;

import dao.ProductoDAO;
import modelo.Producto;

public class Principal {

    public static void main(String[] args) {

        ProductoDAO dao = new ProductoDAO();

        // Crear un producto
        Producto producto = new Producto();

        producto.setCodigo("P001");
        producto.setNombre("Laptop Lenovo");
        producto.setDescripcion("Portátil Core i5");
        producto.setPrecioBase(2500000);
        producto.setPrecioVenta(2900000);
        producto.setCategoria("Computadores");
        producto.setCantidad(10);
        producto.setEstado("ACTIVO");

        // Registrar
        if (dao.registrarProducto(producto)) {
            System.out.println("Producto registrado correctamente.");
        } else {
            System.out.println("No fue posible registrar el producto.");
        }

        // Buscar
        Producto encontrado = dao.buscarProducto("P001");

        if (encontrado != null) {

            System.out.println("Producto encontrado:");
            System.out.println("Código: " + encontrado.getCodigo());
            System.out.println("Nombre: " + encontrado.getNombre());
            System.out.println("Precio de venta: " + encontrado.getPrecioVenta());

            // Actualizar
            encontrado.setPrecioVenta(3000000);
            encontrado.setCantidad(15);

            if (dao.actualizarProducto(encontrado)) {
                System.out.println("Producto actualizado correctamente.");
            } else {
                System.out.println("No fue posible actualizar el producto.");
            }

            // Eliminar lógicamente
            if (dao.eliminarProducto(encontrado.getCodigo())) {
                System.out.println("Producto eliminado lógicamente.");
            } else {
                System.out.println("No fue posible eliminar el producto.");
            }

        } else {
            System.out.println("Producto no encontrado.");
        }
    }
}