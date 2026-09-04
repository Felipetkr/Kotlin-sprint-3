package com.fiap.latteconnect.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fiap.latteconnect.model.CollectionPoint
import com.fiap.latteconnect.ui.components.AppTopBar
import com.fiap.latteconnect.ui.components.InfoCard
import com.fiap.latteconnect.ui.components.MockMapPreview
import com.fiap.latteconnect.ui.components.OpeningHoursRow
import com.fiap.latteconnect.ui.components.PrimaryButton
import com.fiap.latteconnect.ui.components.SectionTitle
import com.fiap.latteconnect.ui.components.StatusPill

@Composable
fun CollectionPointDetailScreen(
    point: CollectionPoint?,
    relatedPoints: List<CollectionPoint>,
    onBackClick: () -> Unit,
    onDonorRegisterClick: () -> Unit,
    onRequestDonationClick: () -> Unit
) {
    Scaffold(topBar = { AppTopBar(title = "Detalhe da unidade", onBackClick = onBackClick) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (point == null) {
                SectionTitle(
                    title = "Unidade não encontrada",
                    subtitle = "O item selecionado não existe na lista mockada atual."
                )
                PrimaryButton(text = "Voltar", onClick = onBackClick)
            } else {
                SectionTitle(title = point.name, subtitle = "${point.type} em ${point.neighborhood}")

                InfoCard {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocalHospital,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(text = point.priority, style = MaterialTheme.typography.titleMedium)
                    }
                    StatusPill(text = "${point.stockLiters} L disponíveis")
                    OpeningHoursRow(text = point.openingHours)
                }

                InfoCard {
                    DetailRow(
                        icon = Icons.Filled.LocationOn,
                        title = "Endereço",
                        value = "${point.address}, ${point.city} - CEP ${point.cep}"
                    )
                    DetailRow(icon = Icons.Filled.Phone, title = "Contato", value = point.phone)
                    Text(
                        text = point.notes,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                MockMapPreview(points = listOf(point) + relatedPoints.filter { it.id != point.id }.take(3))

                InfoCard {
                    Text(text = "Próximos horários de coleta", style = MaterialTheme.typography.titleMedium)
                    point.availableSlots.forEach { slot ->
                        StatusPill(text = slot)
                    }
                }

                PrimaryButton(text = "Cadastrar doadora para esta região", onClick = onDonorRegisterClick)
                OutlinedButton(onClick = onRequestDonationClick, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Abrir solicitação de doação")
                }
            }
        }
    }
}

@Composable
private fun DetailRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
        Column {
            Text(text = title, style = MaterialTheme.typography.labelLarge)
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
