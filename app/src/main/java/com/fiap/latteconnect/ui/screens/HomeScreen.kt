package com.fiap.latteconnect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.fiap.latteconnect.data.LatteMockData
import com.fiap.latteconnect.model.HowItWorksStep
import com.fiap.latteconnect.ui.components.ActionCard
import com.fiap.latteconnect.ui.components.ImpactCard
import com.fiap.latteconnect.ui.components.SectionTitle

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader()
        HeroSection()
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SectionTitle(
                title = "Fluxos principais",
                subtitle = "Dados mockados para demonstrar a experiência da Sprint 3."
            )
            LatteMockData.actionItems.forEach { item ->
                ActionCard(item = item, onClick = { onNavigate(item.route) })
            }
        }
        Spacer(modifier = Modifier.height(28.dp))
        HowItWorksSection()
        Spacer(modifier = Modifier.height(28.dp))
        ImpactSection()
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun HomeHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 20.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
        Column {
            Text(
                text = "LatteConnect",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = "banco de leite humano",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
private fun HeroSection() {
    Column(modifier = Modifier.padding(20.dp)) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(50))
                .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
            Text(
                text = "LEITE HUMANO • VIDA QUE CONECTA",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = heroTitle(), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "O LatteConnect facilita a doação de leite humano, a coleta domiciliar " +
                "e a busca por bancos de leite parceiros usando dados simulados.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun heroTitle() = buildAnnotatedString {
    append("Conectando doadoras, hospitais e famílias que precisam de ")
    withStyle(SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
        append("leite humano")
    }
    append(".")
}

@Composable
private fun HowItWorksSection() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(text = "Como funciona", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(LatteMockData.howItWorksSteps) { step ->
                HowItWorksStepCard(step = step)
            }
        }
    }
}

@Composable
private fun HowItWorksStepCard(step: HowItWorksStep) {
    Column(modifier = Modifier.width(160.dp)) {
        Text(
            text = "${step.order}. ${step.title}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = step.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ImpactSection() {
    Column {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(text = "Nosso impacto", style = MaterialTheme.typography.headlineSmall)
            Text(
                text = "Cada gota faz a diferença.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(LatteMockData.impactStats) { stat ->
                ImpactCard(stat = stat)
            }
        }
    }
}
