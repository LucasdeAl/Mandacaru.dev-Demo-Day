package br.ufc.mandacaru5.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.ufc.mandacaru5.model.Post;
import br.ufc.mandacaru5.util.Properties;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class PostService {
	
	@Autowired
	Properties properties;

	private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";
	private Gson gson;

	public PostService() {
		gson = new Gson();
	}

	public Post findPostByRequest(int id) {
		OkHttpClient client = new OkHttpClient.Builder().build();

		Request request = new Request.Builder().url(BASE_URL + "/" + id).build();

		Call call = client.newCall(request);

		try {
			Response response = call.execute();			
			Post post = gson.fromJson(response.body().string(), Post.class);			
			properties.setPost(post.getTitle());
			
			return post;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Post> findAllPostsByRequest() {
		OkHttpClient client = new OkHttpClient.Builder().build();

		Request request = new Request.Builder().url(BASE_URL).build();

		Call call = client.newCall(request);

		try {
			Response response = call.execute();
			return gson.fromJson(response.body().string(), List.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Post saveByRequest(Post post) {
		OkHttpClient client = new OkHttpClient.Builder().build();

		String postJson = gson.toJson(post);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(postJson, mediaType);

		Request request = new Request.Builder().url(BASE_URL).post(body).build();

		Call call = client.newCall(request);

		try {
			Response response = call.execute();
			return gson.fromJson(response.body().string(), Post.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Post updateByRequest(int id, Post post) {
		OkHttpClient client = new OkHttpClient.Builder().build();

		String postJson = gson.toJson(post);
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(postJson, mediaType);

		Request request = new Request.Builder().url(BASE_URL + "/" + id).put(body).build();

		Call call = client.newCall(request);

		try {
			Response response = call.execute();;
			return gson.fromJson(response.body().string(), Post.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public void deleteByRequest(int id) {
		OkHttpClient client = new OkHttpClient.Builder().build();

		Request request = new Request.Builder().url(BASE_URL + "/" + id).delete().build();

		Call call = client.newCall(request);

		try {
			call.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}