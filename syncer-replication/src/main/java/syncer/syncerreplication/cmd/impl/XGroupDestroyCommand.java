/*
 * Copyright 2016-2018 Leon Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package syncer.syncerreplication.cmd.impl;

/**
 * @author Leon Chen
 * @since 2.6.0
 */
public class XGroupDestroyCommand extends XGroupCommand {
    
    private static final long serialVersionUID = 1L;

    private byte[] group;
    
    public XGroupDestroyCommand() {
    }

    public XGroupDestroyCommand(byte[] key, byte[] group) {
        super(key);
        this.group = group;
    }

    public byte[] getGroup() {
        return group;
    }

    public void setGroup(byte[] group) {
        this.group = group;
    }
}
