package com.wise.volume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wise.core.Query;
import com.wise.volume.Volume;
import com.wise.volume.VolumeRepository;

public class InMemoryVolumeRepository implements VolumeRepository {

	private Map<String, Volume> volumeMap = new HashMap<>();
	
	@Override
	public Volume find(String key) {
		return volumeMap.get(key);
	}

	@Override
	public List<Volume> search(Query query) {
		return new ArrayList<>(volumeMap.values());
	}

	@Override
	public void save(Volume volume) {
		volumeMap.put(volume.getMaster(), volume);
	}

	@Override
	public void remove(Volume volume) {
		volumeMap.remove(volume);
	}
}
