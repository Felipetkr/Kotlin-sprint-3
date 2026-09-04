package com.fiap.latteconnect.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fiap.latteconnect.data.LatteMockData
import com.fiap.latteconnect.ui.components.AppTopBar
import com.fiap.latteconnect.ui.components.CollectionPointCard
import com.fiap.latteconnect.ui.components.InfoCard
import com.fiap.latteconnect.ui.components.OpeningHoursRow
import com.fiap.latteconnect.ui.components.PrimaryButton
import com.fiap.latteconnect.ui.components.SectionTitle
import com.fiap.latteconnect.ui.components.StatusPill

@Composable
fun DonorRegisterScreen(
    onBackClick: () -> Unit,
    onViewHospitalsClick: () -> Unit
) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var cep by rememberSaveable { mutableStateOf("") }
    var neighborhood by rememberSaveable { mutableStateOf("") }
    var selectedSlot by rememberSaveable { mutableStateOf("Amanhã, 9h") }
    var consent by rememberSaveable { mutableStateOf(false) }
    var submitted by rememberSaveable { mutableStateOf(false) }

    val suggestedPoint = LatteMockData.collectionPointsForCep(cep).first()
    val canSubmit = fullName.isNotBlank() && phone.isNotBlank() && cep.length >= 8 && consent

    Scaffold(topBar = { AppTopBar(title = "Cadastro de doadora", onBackClick = onBackClick) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionTitle(
                title = "Quero doar leite",
                subtitle = "Preencha os dados principais para simular o cadastro e o agendamento da coleta."
            )

            if (submitted) {
                InfoCard {
                    StatusPill(text = "Cadastro enviado")
                    Text(
                        text = "Obrigada, $fullName. Sua disponibilidade foi enviada para ${suggestedPoint.name}.",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Coleta simulada: $selectedSlot. A equipe entraria em contato pelo telefone $phone para confirmar orientações de segurança.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                CollectionPointCard(point = suggestedPoint, onClick = onViewHospitalsClick)
                OutlinedButton(
                    onClick = {
                        submitted = false
                        fullName = ""
                        phone = ""
                        email = ""
                        cep = ""
                        neighborhood = ""
                        consent = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Fazer novo cadastro")
                }
                PrimaryButton(text = "Ver hospitais parceiros", onClick = onViewHospitalsClick)
            } else {
                InfoCard {
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = { Text("Nome completo") },
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
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("E-mail") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = cep,
                        onValueChange = { cep = it },
                        label = { Text("CEP para coleta") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = neighborhood,
                        onValueChange = { neighborhood = it },
                        label = { Text("Bairro") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                InfoCard {
                    Text(text = "Disponibilidade", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "Escolha um horário mockado para demonstrar o fluxo de coleta domiciliar.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        suggestedPoint.availableSlots.forEach { slot ->
                            FilterChip(
                                selected = selectedSlot == slot,
                                onClick = { selectedSlot = slot },
                                label = { Text(text = slot) }
                            )
                        }
                    }
                    OpeningHoursRow(text = suggestedPoint.openingHours)
                }

                InfoCard {
                    Text(text = "Ponto sugerido pelo CEP", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "A busca é simulada localmente, priorizando pontos com CEP mais próximo.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                CollectionPointCard(point = suggestedPoint, onClick = onViewHospitalsClick)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Checkbox(checked = consent, onCheckedChange = { consent = it })
                    Text(
                        text = "Autorizo o uso dos dados informados apenas para simular o contato do banco de leite parceiro neste MVP.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                    )
                }

                PrimaryButton(
                    text = "Enviar cadastro simulado",
                    enabled = canSubmit,
                    onClick = { submitted = true }
                )
            }
        }
    }
}
