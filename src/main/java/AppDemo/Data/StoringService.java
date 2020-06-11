package AppDemo.Data;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;

public class StoringService {

	private Datastore datastore = null;
	
	public StoringService(String projectID)
	{
		this.datastore = DatastoreOptions.newBuilder().setProjectId(projectID).build().getService();
	}
	
	public boolean addUser(AppDemo.Data.User user, String type)
	{
		Key taskKey = datastore.newKeyFactory().setKind(type).newKey(user.getUsername());
		Entity retrieved = datastore.get(taskKey);
		if(retrieved != null)
		{
			return false;
		}
		else
		{
			Entity new_user = Entity.newBuilder(taskKey)
			        .set("password", user.getPassword())
			        .build();
			datastore.put(new_user);
			return true;
		}
	}
	
	public AppDemo.Data.User getUser(String username, String type)
	{
		Key taskKey = datastore.newKeyFactory().setKind(type).newKey(username);
		Entity retrieved = datastore.get(taskKey);
		if(retrieved != null)
		{
			return new AppDemo.Data.User(username, retrieved.getString("password"));
		}
		else
		{
			return null;
		}
	}
	
	
	
}
