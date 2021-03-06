package com.wise.volume.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wise.core.Query;
import com.wise.volume.Volume;
import com.wise.volume.VolumeRepository;

public class MockVolumeRepository implements VolumeRepository {

	private Map<String, Volume> volumeMap = new HashMap<>();
	
	@Override
	public Volume find(String key) {
		return volumeMap.get(key);
	}

	@Override
	public List<Volume> search(Query query) {
		return null;
	}

	@Override
	public void save(Volume volume) {
		volumeMap.put(volume.getMaster(), volume);
	}

	@Override
	public void remove(Volume domain) {
		
	}
}
