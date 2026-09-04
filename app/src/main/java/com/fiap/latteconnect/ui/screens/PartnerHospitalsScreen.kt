package com.fiap.latteconnect.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fiap.latteconnect.data.LatteMockData
import com.fiap.latteconnect.ui.components.AppTopBar
import com.fiap.latteconnect.ui.components.CollectionPointCard
import com.fiap.latteconnect.ui.components.InfoCard
import com.fiap.latteconnect.ui.components.MockMapPreview
import com.fiap.latteconnect.ui.components.SectionTitle
import com.fiap.latteconnect.ui.components.StatusPill

@Composable
fun PartnerHospitalsScreen(
    onBackClick: () -> Unit,
    onPointClick: (String) -> Unit
) {
    var cep by rememberSaveable { mutableStateOf("04037-000") }
    val points = LatteMockData.collectionPointsForCep(cep)

    Scaffold(topBar = { AppTopBar(title = "Hospitais parceiros", onBackClick = onBackClick) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionTitle(
                title = "Pontos de coleta por CEP",
                subtitle = "Lista mockada de bancos de leite e hospitais parceiros conectados ao LatteConnect."
            )

            InfoCard {
                OutlinedTextField(
                    value = cep,
                    onValueChange = { cep = it },
                    label = { Text("Digite um CEP") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "A ordenação é simulada localmente para demonstrar a busca sem usar API de mapas.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            MockMapPreview(points = points)

            SectionTitle(
                title = "Unidades encontradas",
                subtitle = "${points.size} pontos mockados priorizados para o CEP informado."
            )

            points.forEach { point ->
                CollectionPointCard(point = point, onClick = { onPointClick(point.id) })
            }

            InfoCard {
                StatusPill(text = "Sem backend nesta Sprint")
                Text(
                    text = "Os endereços, estoques, horários e distâncias são dados simulados para representar o fluxo principal do produto.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
