# The League Standard

**A modern full-stack web application for WNBA news, statistics, and team information**

*"This ain't your daddy's basketball"*

## 🏀 Overview

The League Standard is a comprehensive WNBA platform that delivers real-time statistics, breaking news, and in-depth team information through a sleek, responsive web interface. Built with modern technologies, it provides fans with everything they need to stay connected to women's professional basketball.

I created this project August 8th and presented it as a final project for the 2025 Apprenti Software Engineer Bootcamp that lead into the Visa Software Engineer Apprenticeship.

## ✨ Features

### 📊 **Live Data & Statistics**
- Game scores and schedules
- League standings and team rankings
- Player profiles and statistics
- Team information with logos, colors, and history

### 📰 **News & Media**
- WNBA news articles
- Image carousel displays
- Team Logos and Player Media-Day Photos

### 👤 **Personalization**
- Bookmark favorite games and news articles
- Follow favorite teams and players

### 📱 **Modern User Experience**
  Responsive design for all devices
- Fast loading with intelligent caching
- Intuitive navigation with mobile-first approach
- Clean, professional interface with team branding

## 🛠️ Technology Stack

### **Frontend**
- **React 19** - Modern component architecture with hooks
- **React Router 7** - Client-side routing and navigation
- **TanStack Query** - Data fetching, caching, and synchronization
- **Bootstrap 5** - Responsive UI framework
- **Vite** - Fast development build tool

### **Backend**
- **Spring Boot 3.5.4** - Enterprise Java framework
- **Java 21** - Latest LTS Java version
- **Maven** - Dependency management and build tool
- **MySQL** - Relational database with JDBC

### **External Integrations**
- **WNBA API** - Official league data for games, teams, and players
- **ESPN News API** - WNBA news and updates

## 🚀 Quick Start

### Prerequisites
- **Java 21+**
- **Node.js 20+**
- **MySQL 8.0+**
- **Maven 3.6+**

### 1. Clone the Repository
```bash
git clone <repository-url>
cd league-standard
```

### 2. Database Setup
Create the MySQL database and run the schema files:
```sql
CREATE DATABASE the_league_standard;
```

Execute the SQL files in order:
```bash
# Run these files in sequence:
league-standard-backend/src/main/resources/data/SQL\ Queries/the_league_standard*.sql
```

### 3. Backend Configuration
Create `league-standard-backend/src/main/resources/application.properties`:
```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/the_league_standard
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# External APIs
wnba.api.key=your_wnba_api_key
wnba.api.base.url=https://api.sportradar.com/wnba/trial/v8/en/
news.api.key=your_rapidapi_key
news.api.base.url=https://wnba-api.p.rapidapi.com/news

# Server
server.port=8080
```

### 4. Start the Backend
```bash
cd league-standard-backend
./mvnw spring-boot:run
```
Backend will be available at `http://localhost:8080`

### 5. Start the Frontend
```bash
cd league-standard-frontend
npm install
npm run dev
```
Frontend will be available at `http://localhost:5173`

## 🏗️ Application Architecture

### **Full-Stack Data Flow**
```
[WNBA API] ─┐
           ├─→ [Spring Boot Backend] ─→ [MySQL Database]
[News API] ─┘              │
                          │
                         API
                          │
[React Frontend] ←────────┘
     │
[TanStack Query Cache]
     │
[User Interface]
```

### **Backend Architecture**
```
Controllers/     # REST API endpoints with CORS support
├── BookmarksController    # User bookmark management
├── FavoritesController    # User favorites functionality  
├── GamesController        # Game schedules and scores
├── NewsController         # News articles and media
├── PlayersController      # Player profiles and stats
├── StandingsController    # League standings
├── TeamColorsController   # Team branding colors
└── TeamsController        # Team information

Services/        # Business logic and external API integration
├── APIService            # WNBA API communication
├── GameDataService       # Game data processing
├── NewsDataService       # News data processing
├── PlayerService         # Player data management
└── StandingsDataService  # Standings calculations

Data/           # Repository pattern with MySQL
├── Repositories         # Data access interfaces
├── Implementations      # MySQL-specific implementations
└── Mappers             # SQL result mapping
```

### **Frontend Architecture**
```
src/
├── components/          # React component library
│   ├── Context/        # Global state management
│   ├── Games/          # Game-related components
│   ├── News/           # News and media components
│   ├── Players/        # Player profile components
│   ├── Standings/      # League standings components
│   ├── Teams/          # Team information components
│   └── UI/             # Reusable UI components
├── hooks/              # Custom React hooks for data
├── api/                # Backend API integration
├── utils/              # Utility functions
└── constants/          # Configuration and constants
```

## 🔌 API Reference

### **Core Endpoints**

| Method | Endpoint                   | Description              |
| ------ | -------------------------- | ------------------------ |
| GET    | `/api/teams`               | List all WNBA teams      |
| GET    | `/api/teams/{id}`          | Get team details         |
| GET    | `/api/players`             | List all players         |
| GET    | `/api/players/{id}`        | Get player profile       |
| GET    | `/api/games`               | List all games           |
| GET    | `/api/games/next/{teamId}` | Get team's next game     |
| GET    | `/api/standings`           | Current league standings |
| GET    | `/api/news`                | Latest news articles     |

### **User Features**

| Method | Endpoint                    | Description           |
| ------ | --------------------------- | --------------------- |
| POST   | `/api/favorites/teams/{id}` | Add team to favorites |
| DELETE | `/api/favorites/teams/{id}` | Remove team favorite  |
| POST   | `/api/bookmarks/news/{id}`  | Bookmark news article |
| GET    | `/api/bookmarks`            | Get user bookmarks    |

## 🎨 Design System

### **Component Philosophy**
- **Reusable Components**: Modular design for consistent UI
- **Responsive First**: Mobile-optimized with progressive enhancement  
- **Team Branding**: Dynamic colors and logos for each team
- **Accessibility**: Semantic HTML and ARIA support

### **Key UI Components**
- `<TeamLogo>` - Dynamic team logos with multiple sizes
- `<PlayerDetail>` - Rich player profile cards
- `<GameElement>` - Interactive game cards with bookmarking
- `<Gallery>` - Image carousels for news articles
- `<FavoritesMenu>` - Personalized user dashboard

## 💾 Data Management

### **Caching Strategy**
- **Frontend**: TanStack Query with 24-hour cache for static data
- **Backend**: Service-level caching for external API responses
- **Database**: Optimized indexes for frequent queries

### **Data Population**
Populate initial data by uncommenting lines in `TheLeagueStandardApplication.java`:
```java
// gameDataService.loadGamesFromAPI(2025);
// standingsDataService.fetchStandings(2025, "REG");
// playerService.fetchAllPlayers();
// newsDataService.loadNewsData();
```

## 🌐 Deployment

### **Environment Setup**
- Configure database connections for production
- Set up API keys for external services
- Update CORS origins for production frontend URL
- Configure server ports and SSL certificates

### **Production Considerations**
- Enable database connection pooling
- Implement API rate limiting
- Set up monitoring and logging
- Configure CDN for static assets

## 🔧 Development

### **Code Organization**
- **Backend**: Clean architecture with separation of concerns
- **Frontend**: Component-based architecture with custom hooks
- **Database**: Normalized schema with proper indexing
- **APIs**: RESTful design with consistent response formats

### **Best Practices**
- TypeScript-ready React components
- Proper error handling and loading states
- Responsive design with mobile-first approach
- Efficient SQL queries with JOIN operations

## 📱 Mobile Experience

The application is fully responsive with:
- Touch-friendly navigation
- Optimized images and loading
- Mobile-specific UI patterns
- Offline-capable caching

## 🤝 Contributing

This project demonstrates modern full-stack development:
- Enterprise Java backend architecture
- Modern React frontend patterns  
- RESTful API design
- Database optimization
- External API integration
- Responsive web design

---

**Experience the future of WNBA coverage with The League Standard** 🏆