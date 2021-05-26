
package com.emergentes.dao;
import com.emergentes.modelo.Permiso;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PermisoDAOimpl extends ConexionDB implements PermisoDAO {
    @Override
    public void insert(Permiso permiso) throws Exception {
        try {
            this.conectar();
            String sql = "insert into permisos (id_usuario, id_rol) values (?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, permiso.getUsuario_id());
            ps.setInt(2, permiso.getRol_id());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Permiso permiso) throws Exception {
        try {
            this.conectar();
            String sql = "update permisos set id_usuario = ?, id_rol = ? where id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, permiso.getUsuario_id());
            ps.setInt(2, permiso.getRol_id());
            ps.setInt(3, permiso.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "delete from permisos where id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Permiso getById(int id) throws Exception {
        Permiso permiso = new Permiso();
        try {
            this.conectar();
            String sql = "select * from permisos where id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                permiso.setId(rs.getInt("id"));
                permiso.setUsuario_id(rs.getInt("id_usuario"));
                permiso.setRol_id(rs.getInt("id_rol"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return permiso;
    }

    @Override
    public List<Permiso> getAll() throws Exception {
        List<Permiso> lista = null;
        try {
            this.conectar();
            String sql = "select p.*,u.usuario as usuario, r.descripcion as descripcion from permisos p ";
            sql += "LEFT JOIN usuarios u ON p.id_usuario = u.id ";
            sql += "LEFT JOIN roles r ON p.id_rol = r.id";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Permiso>();
            while (rs.next()) {
                Permiso permiso = new Permiso();
                permiso.setId(rs.getInt("id"));
                permiso.setUsuario_id(rs.getInt("id_usuario"));
                permiso.setRol_id(rs.getInt("id_rol"));
                permiso.setUsuario(rs.getString("usuario"));
                permiso.setDescripcion(rs.getString("descripcion"));
                lista.add(permiso);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }    
}
