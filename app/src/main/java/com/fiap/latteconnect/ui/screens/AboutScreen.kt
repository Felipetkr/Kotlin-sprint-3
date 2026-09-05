package com.fiap.latteconnect.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fiap.latteconnect.data.LatteMockData
import com.fiap.latteconnect.ui.components.AppTopBar
import com.fiap.latteconnect.ui.components.InfoCard
import com.fiap.latteconnect.ui.components.SectionTitle
import com.fiap.latteconnect.ui.components.StatusPill

@Composable
fun AboutScreen(onBackClick: () -> Unit) {
    Scaffold(topBar = { AppTopBar(title = "Sobre o projeto", onBackClick = onBackClick) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionTitle(
                title = "LatteConnect",
                subtitle = "MVP Android em Kotlin para conectar doadoras, hospitais e famílias."
            )

            InfoCard {
                StatusPill(text = "Problema do pitch")
                Text(
                    text = "A comunicação e o engajamento para doação de leite humano ainda dependem de canais fragmentados. Isso dificulta o cadastro de nutrizes, a localização de pontos de coleta e o acesso de famílias que precisam de apoio.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            InfoCard {
                StatusPill(text = "Priorizado na Sprint 3")
                Text(
                    text = "O MVP navegável implementa cadastro de doadora, solicitação de doação, busca de hospitais por CEP, detalhes de unidade, tela informativa e painel operacional com dados mockados.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            InfoCard {
                StatusPill(text = "Fora do escopo desta Sprint")
                Text(
                    text = "IA consultiva, notificações reais, gamificação persistente, Firebase, API, banco local e mapa com geolocalização real ficam preparados como evolução, mas não são exigidos nesta entrega.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            SectionTitle(title = "Dúvidas frequentes")
            LatteMockData.faqItems.forEach { item ->
                InfoCard {
                    Text(text = item.question, style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = item.answer,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
