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
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fiap.latteconnect.data.LatteMockData
import com.fiap.latteconnect.ui.components.AppTopBar
import com.fiap.latteconnect.ui.components.InfoCard
import com.fiap.latteconnect.ui.components.MetricCard
import com.fiap.latteconnect.ui.components.SectionTitle
import com.fiap.latteconnect.ui.components.StatusPill

@Composable
fun DashboardScreen(onBackClick: () -> Unit) {
    Scaffold(topBar = { AppTopBar(title = "Painel do MVP", onBackClick = onBackClick) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionTitle(
                title = "Operação simulada",
                subtitle = "Indicadores mockados para demonstrar como gestores acompanhariam a rede."
            )

            InfoCard {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Column {
                        Text(
                            text = "Alerta: queda de doações prevista",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Dados simulados indicam risco de queda de 10% nas doações da próxima semana.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            LatteMockData.dashboardMetrics.forEach { metric ->
                MetricCard(metric = metric)
            }

            InfoCard {
                Text(text = "Cadastros recentes", style = MaterialTheme.typography.titleMedium)
                LatteMockData.recentDonors.forEachIndexed { index, donor ->
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = donor.name, style = MaterialTheme.typography.labelLarge)
                                Text(
                                    text = "${donor.neighborhood} • ${donor.registeredAt}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            StatusPill(text = donor.status)
                        }
                        if (index < LatteMockData.recentDonors.lastIndex) {
                            HorizontalDivider()
                        }
                    }
                }
            }

            InfoCard {
                Text(text = "Estoque por unidade", style = MaterialTheme.typography.titleMedium)
                LatteMockData.collectionPoints.forEach { point ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = point.name, style = MaterialTheme.typography.labelLarge)
                            Text(
                                text = point.neighborhood,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        StatusPill(text = "${point.stockLiters} L")
                    }
                }
            }
        }
    }
}
