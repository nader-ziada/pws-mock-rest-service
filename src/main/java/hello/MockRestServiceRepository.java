package hello;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MockRestServiceRepository extends MongoRepository<MockRestService, String> {

    public MockRestService findByName(String name);
    public MockRestService findById(String id);

}