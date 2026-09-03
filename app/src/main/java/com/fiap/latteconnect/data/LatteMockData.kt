package com.fiap.latteconnect.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.VolunteerActivism
import com.fiap.latteconnect.model.ActionItem
import com.fiap.latteconnect.model.CollectionPoint
import com.fiap.latteconnect.model.DashboardMetric
import com.fiap.latteconnect.model.FaqItem
import com.fiap.latteconnect.model.HowItWorksStep
import com.fiap.latteconnect.model.ImpactStat
import com.fiap.latteconnect.model.RecentDonor
import com.fiap.latteconnect.navigation.Routes

object LatteMockData {

    val actionItems = listOf(
        ActionItem(
            title = "Quero doar leite",
            description = "Cadastre-se como nutriz doadora e simule o agendamento da coleta.",
            icon = Icons.Filled.VolunteerActivism,
            route = Routes.DONOR_REGISTER
        ),
        ActionItem(
            title = "Preciso de doação",
            description = "Abra uma solicitação simulada para um bebê em acompanhamento.",
            icon = Icons.Filled.FamilyRestroom,
            route = Routes.REQUEST_DONATION
        ),
        ActionItem(
            title = "Hospitais parceiros",
            description = "Busque pontos de coleta por CEP e veja os detalhes de cada unidade.",
            icon = Icons.Filled.LocalHospital,
            route = Routes.PARTNER_HOSPITALS
        ),
        ActionItem(
            title = "Painel mockado",
            description = "Acompanhe estoque, cadastros recentes e alertas da operação.",
            icon = Icons.Filled.Dashboard,
            route = Routes.DASHBOARD
        ),
        ActionItem(
            title = "Sobre o projeto",
            description = "Entenda o problema, o escopo do MVP e a evolução planejada.",
            icon = Icons.Filled.Info,
            route = Routes.ABOUT
        )
    )

    val howItWorksSteps = listOf(
        HowItWorksStep(
            order = 1,
            title = "Cadastro",
            description = "A nutriz informa dados de contato, endereço e disponibilidade."
        ),
        HowItWorksStep(
            order = 2,
            title = "Ponto mais próximo",
            description = "O app sugere bancos de leite parceiros de acordo com o CEP."
        ),
        HowItWorksStep(
            order = 3,
            title = "Coleta segura",
            description = "A coleta domiciliar é simulada com data, horário e hospital responsável."
        ),
        HowItWorksStep(
            order = 4,
            title = "Distribuição",
            description = "O leite é destinado a bebês acompanhados pela rede de bancos de leite."
        )
    )

    val impactStats = listOf(
        ImpactStat(value = "219 mil+", label = "bebês beneficiados no Brasil em 2025"),
        ImpactStat(value = "237", label = "bancos de leite humanos no país"),
        ImpactStat(value = "249", label = "pontos de coleta integrados à rede"),
        ImpactStat(value = "1 L", label = "pode alimentar até 10 recém-nascidos por dia")
    )

    val collectionPoints = listOf(
        CollectionPoint(
            id = "santa-clara",
            name = "Banco de Leite Santa Clara",
            type = "Hospital maternidade",
            neighborhood = "Vila Mariana",
            address = "Rua das Flores, 123",
            city = "São Paulo/SP",
            cep = "04037-000",
            distanceKm = 1.8,
            phone = "(11) 99999-1234",
            openingHours = "Segunda a sexta, 8h às 17h",
            stockLiters = 45,
            priority = "Alta demanda neonatal",
            availableSlots = listOf("Hoje, 14h", "Amanhã, 9h", "Amanhã, 15h"),
            notes = "Equipe disponível para orientação por WhatsApp e retirada domiciliar."
        ),
        CollectionPoint(
            id = "vila-esperanca",
            name = "Hospital Vila Esperança",
            type = "Banco de leite humano",
            neighborhood = "Mooca",
            address = "Av. Paes de Barros, 960",
            city = "São Paulo/SP",
            cep = "03114-000",
            distanceKm = 4.2,
            phone = "(11) 98888-2040",
            openingHours = "Todos os dias, 7h às 19h",
            stockLiters = 32,
            priority = "Estoque em atenção",
            availableSlots = listOf("Hoje, 16h", "Quarta, 10h", "Sexta, 8h"),
            notes = "Recebe doações presenciais e organiza rota de coleta em bairros próximos."
        ),
        CollectionPoint(
            id = "materno-leste",
            name = "Maternidade Materno Leste",
            type = "Ponto de coleta",
            neighborhood = "Tatuapé",
            address = "Rua Itapura, 540",
            city = "São Paulo/SP",
            cep = "03310-000",
            distanceKm = 6.7,
            phone = "(11) 97777-4401",
            openingHours = "Segunda a sábado, 9h às 18h",
            stockLiters = 21,
            priority = "Solicitações de UTI neonatal",
            availableSlots = listOf("Quinta, 11h", "Quinta, 17h", "Sábado, 9h"),
            notes = "Indicado para famílias que precisam acompanhar solicitação em maternidade."
        ),
        CollectionPoint(
            id = "humaniza-oeste",
            name = "Instituto Humaniza Oeste",
            type = "Hospital parceiro",
            neighborhood = "Pinheiros",
            address = "Rua Cardeal Arcoverde, 1580",
            city = "São Paulo/SP",
            cep = "05408-002",
            distanceKm = 8.1,
            phone = "(11) 96666-8700",
            openingHours = "Segunda a sexta, 8h às 16h",
            stockLiters = 58,
            priority = "Estoque estável",
            availableSlots = listOf("Amanhã, 13h", "Sexta, 10h", "Sexta, 15h"),
            notes = "Unidade com triagem educativa para novas doadoras e familiares."
        )
    )

    val dashboardMetrics = listOf(
        DashboardMetric(
            label = "Estoque disponível",
            value = "156 L",
            trend = "+12 L na semana",
            supportingText = "Soma dos quatro pontos parceiros mockados."
        ),
        DashboardMetric(
            label = "Doadoras ativas",
            value = "124",
            trend = "+18% vs. mês anterior",
            supportingText = "Cadastros com disponibilidade confirmada."
        ),
        DashboardMetric(
            label = "Solicitações abertas",
            value = "37",
            trend = "9 urgentes",
            supportingText = "Pedidos simulados aguardando distribuição."
        ),
        DashboardMetric(
            label = "Análises pendentes",
            value = "18",
            trend = "triagem hospitalar",
            supportingText = "Etapa de segurança antes da distribuição."
        )
    )

    val recentDonors = listOf(
        RecentDonor("Ana Souza", "Vila Clementino", "25/08/2026 - 09:42", "Ativa"),
        RecentDonor("Julia Lima", "Mooca", "25/08/2026 - 08:15", "Em análise"),
        RecentDonor("Mariana Costa", "Santana", "24/08/2026 - 16:33", "Pendente"),
        RecentDonor("Carla Menezes", "Pinheiros", "24/08/2026 - 11:07", "Ativa")
    )

    val requestProfiles = listOf(
        "Bebê prematuro em UTI neonatal",
        "Recém-nascido com baixo peso",
        "Família encaminhada por hospital parceiro",
        "Apoio temporário por orientação médica"
    )

    val faqItems = listOf(
        FaqItem(
            question = "Quem pode doar?",
            answer = "Nutrizes saudáveis, com produção excedente e acompanhamento médico podem iniciar o cadastro."
        ),
        FaqItem(
            question = "O leite é analisado?",
            answer = "Sim. A triagem e o controle de qualidade ficam sob responsabilidade do banco de leite parceiro."
        ),
        FaqItem(
            question = "O app usa dados reais?",
            answer = "Nesta Sprint, todos os dados são mockados para demonstrar o fluxo do MVP sem backend."
        )
    )

    fun collectionPointById(id: String?): CollectionPoint? {
        return collectionPoints.firstOrNull { point -> point.id == id }
    }

    fun collectionPointsForCep(cep: String): List<CollectionPoint> {
        val digits = cep.filter { char -> char.isDigit() }
        if (digits.length < 3) return collectionPoints

        val prefix = digits.take(2)
        return collectionPoints.sortedWith(
            compareBy<CollectionPoint> { point ->
                val pointPrefix = point.cep.filter { char -> char.isDigit() }.take(2)
                if (pointPrefix == prefix) 0 else 1
            }.thenBy { point -> point.distanceKm }
        )
    }
}
