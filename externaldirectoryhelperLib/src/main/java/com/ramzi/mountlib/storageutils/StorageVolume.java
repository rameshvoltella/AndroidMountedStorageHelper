

package com.ramzi.mountlib.storageutils;

import java.io.File;

public final class StorageVolume {

    private final String label;
    private final String path;
    public boolean isEmulated;
    public boolean isPrimary;
    public int uuid;

    public StorageVolume(String label, String path) {
        this.label = label;
        this.path = path;
    }

    public String getLabel() {
        return label;
    }

    public String getPath() {
        return path;
    }

	public File getPathFile() {
		return new File(path);
	}

	public String getUuid() {
		return uuid+"";
	}
}