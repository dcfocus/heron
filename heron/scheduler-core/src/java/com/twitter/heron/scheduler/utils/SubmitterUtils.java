//  Copyright 2017 Twitter. All rights reserved.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
package com.twitter.heron.scheduler.utils;

import com.twitter.heron.api.generated.TopologyAPI;
import com.twitter.heron.common.basics.PackageType;
import com.twitter.heron.spi.common.Config;
import com.twitter.heron.spi.common.Key;

public final class SubmitterUtils {

  /**
   * Create the topology config
   *
   * @param topologyPackagePath path to the tar ball containing user submitted jar/tar, defn and config
   * @param topologyBinaryFile name of the user submitted topology jar/tar/pex file
   * @param topologyDefinitionPath path to the topology definition file
   * @param topology proto in memory version of topology definition
   * @return config the topology config
   */
  public static Config topologyConfigs(String topologyPackagePath, String topologyBinaryFile,
        String topologyDefinitionPath, TopologyAPI.Topology topology) {
    PackageType packageType = PackageType.getPackageType(topologyBinaryFile);

    return Config.newBuilder()
        .put(Key.TOPOLOGY_ID, topology.getId())
        .put(Key.TOPOLOGY_NAME, topology.getName())
        .put(Key.TOPOLOGY_DEFINITION_FILE, topologyDefinitionPath)
        .put(Key.TOPOLOGY_PACKAGE_FILE, topologyPackagePath)
        .put(Key.TOPOLOGY_BINARY_FILE, topologyBinaryFile)
        .put(Key.TOPOLOGY_PACKAGE_TYPE, packageType)
        .build();
  }

  private SubmitterUtils() {
  }
}
