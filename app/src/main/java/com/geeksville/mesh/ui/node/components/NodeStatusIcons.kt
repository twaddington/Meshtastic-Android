/*
 * Copyright (c) 2025 Meshtastic LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.geeksville.mesh.ui.node.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NoCell
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.twotone.CloudDone
import androidx.compose.material.icons.twotone.CloudOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.geeksville.mesh.R
import com.geeksville.mesh.ui.common.theme.StatusColors.StatusGreen
import com.geeksville.mesh.ui.common.theme.StatusColors.StatusRed
import com.geeksville.mesh.ui.common.theme.StatusColors.StatusYellow

@Suppress("LongMethod")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NodeStatusIcons(isThisNode: Boolean, isUnmessageable: Boolean, isFavorite: Boolean, isConnected: Boolean) {
    Row(modifier = Modifier.padding(4.dp)) {
        if (isThisNode) {
            TooltipBox(
                positionProvider = TooltipDefaults.rememberTooltipPositionProvider(),
                tooltip = {
                    PlainTooltip {
                        Text(
                            stringResource(
                                if (isConnected) {
                                    R.string.connected
                                } else {
                                    R.string.disconnected
                                },
                            ),
                        )
                    }
                },
                state = rememberTooltipState(),
            ) {
                if (isConnected) {
                    @Suppress("MagicNumber")
                    Icon(
                        imageVector = Icons.TwoTone.CloudDone,
                        contentDescription = stringResource(R.string.connected),
                        modifier = Modifier.size(24.dp), // Smaller size for badge
                        tint = MaterialTheme.colorScheme.StatusGreen,
                    )
                } else {
                    Icon(
                        imageVector = Icons.TwoTone.CloudOff,
                        contentDescription = stringResource(R.string.not_connected),
                        modifier = Modifier.size(24.dp), // Smaller size for badge
                        tint = MaterialTheme.colorScheme.StatusRed,
                    )
                }
            }
        }

        if (isUnmessageable) {
            TooltipBox(
                positionProvider = TooltipDefaults.rememberTooltipPositionProvider(),
                tooltip = { PlainTooltip { Text(stringResource(R.string.unmonitored_or_infrastructure)) } },
                state = rememberTooltipState(),
            ) {
                IconButton(onClick = {}, modifier = Modifier.size(24.dp)) {
                    Icon(
                        imageVector = Icons.Rounded.NoCell,
                        contentDescription = stringResource(R.string.unmessageable),
                        modifier = Modifier.size(24.dp), // Smaller size for badge
                    )
                }
            }
        }
        if (isFavorite && !isThisNode) {
            TooltipBox(
                positionProvider = TooltipDefaults.rememberTooltipPositionProvider(),
                tooltip = { PlainTooltip { Text(stringResource(R.string.favorite)) } },
                state = rememberTooltipState(),
            ) {
                IconButton(onClick = {}, modifier = Modifier.size(24.dp)) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = stringResource(R.string.favorite),
                        modifier = Modifier.size(24.dp), // Smaller size for badge
                        tint = MaterialTheme.colorScheme.StatusYellow,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun StatusIconsPreview() {
    NodeStatusIcons(isThisNode = true, isUnmessageable = true, isFavorite = true, isConnected = false)
}
