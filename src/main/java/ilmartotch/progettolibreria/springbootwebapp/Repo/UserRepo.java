package ilmartotch.progettolibreria.springbootwebapp.Repo;

import ilmartotch.progettolibreria.springbootwebapp.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
}
