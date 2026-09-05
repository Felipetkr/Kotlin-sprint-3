package com.fiap.latteconnect.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
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
import com.fiap.latteconnect.ui.components.PrimaryButton
import com.fiap.latteconnect.ui.components.SectionTitle
import com.fiap.latteconnect.ui.components.StatusPill

@Composable
fun DonationRequestScreen(
    onBackClick: () -> Unit,
    onViewHospitalsClick: () -> Unit
) {
    var responsibleName by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var hospitalReference by rememberSaveable { mutableStateOf("") }
    var cep by rememberSaveable { mutableStateOf("") }
    var selectedProfile by rememberSaveable { mutableStateOf(LatteMockData.requestProfiles.first()) }
    var urgent by rememberSaveable { mutableStateOf(true) }
    var submitted by rememberSaveable { mutableStateOf(false) }

    val closestPoint = LatteMockData.collectionPointsForCep(cep).first()
    val canSubmit = responsibleName.isNotBlank() && phone.isNotBlank() && cep.length >= 8

    Scaffold(topBar = { AppTopBar(title = "Preciso de doação", onBackClick = onBackClick) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionTitle(
                title = "Solicitação de leite humano",
                subtitle = "Fluxo simulado para famílias e hospitais que precisam localizar apoio rapidamente."
            )

            if (submitted) {
                InfoCard {
                    StatusPill(text = if (urgent) "Prioridade alta" else "Solicitação registrada")
                    Text(text = "Protocolo LCT-2026-037", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "A solicitação de $responsibleName foi vinculada a ${closestPoint.name}. O retorno seria feito pelo telefone $phone.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                CollectionPointCard(point = closestPoint, onClick = onViewHospitalsClick)
                OutlinedButton(
                    onClick = { submitted = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Editar solicitação")
                }
                PrimaryButton(text = "Ver rede parceira", onClick = onViewHospitalsClick)
            } else {
                InfoCard {
                    OutlinedTextField(
                        value = responsibleName,
                        onValueChange = { responsibleName = it },
                        label = { Text("Responsável pelo bebê") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Telefone / WhatsApp") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = hospitalReference,
                        onValueChange = { hospitalReference = it },
                        label = { Text("Hospital ou maternidade de referência") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = cep,
                        onValueChange = { cep = it },
                        label = { Text("CEP da família ou hospital") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                InfoCard {
                    Text(text = "Perfil da solicitação", style = MaterialTheme.typography.titleMedium)
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        LatteMockData.requestProfiles.forEach { profile ->
                            FilterChip(
                                selected = selectedProfile == profile,
                                onClick = { selectedProfile = profile },
                                label = { Text(text = profile) }
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Marcar como urgente", style = MaterialTheme.typography.bodyLarge)
                        Switch(checked = urgent, onCheckedChange = { urgent = it })
                    }
                }

                InfoCard {
                    Text(text = "Banco de leite indicado", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "Com base no CEP informado, o MVP sugere uma unidade parceira para avaliação.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                CollectionPointCard(point = closestPoint, onClick = onViewHospitalsClick)

                PrimaryButton(
                    text = "Enviar solicitação simulada",
                    enabled = canSubmit,
                    onClick = { submitted = true }
                )
            }
        }
    }
}
