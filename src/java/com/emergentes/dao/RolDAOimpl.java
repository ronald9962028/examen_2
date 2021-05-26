
package com.emergentes.dao;
import com.emergentes.modelo.Rol;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class RolDAOimpl extends ConexionDB implements RolDAO {
    @Override
    public void insert(Rol rol) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("insert into roles (descripcion) values (?)");
            ps.setString(1, rol.getDescripcion());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }    
    }
    @Override
    public void update(Rol rol) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("update roles set descripcion = ? where id = ?");
            ps.setString(1, rol.getDescripcion());
            ps.setInt(2, rol.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("delete from  roles where id = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Rol getById(int id) throws Exception {
        Rol rol = new Rol();
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM roles WHERE id = ?");
            ps.setInt(1,id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                rol.setId(rs.getInt("id"));
                rol.setDescripcion(rs.getString("descripcion"));
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return rol;
    }

    @Override
    public List<Rol> getAll() throws Exception {
        List<Rol> lista = null;
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM roles");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Rol>();
            while (rs.next()){
                Rol rol =  new Rol();
                
                rol.setId(rs.getInt("id"));
                rol.setDescripcion(rs.getString("descripcion"));
                
                lista.add(rol);
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
