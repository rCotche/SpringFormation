/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import com.example.SpringMVCnetbeans.Entity.Compte;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rcotche.cdi
 */
@Repository
public interface CompteRepository extends PagingAndSortingRepository<Compte, Long>{
    
    @Override
    public List<Compte> findAll();
    
    public Compte findOneById(Long id);
    public Compte findOneByPassword(String password);
}