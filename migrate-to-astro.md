# Migrate kodein-net from Kotlin/JS to Astro

## Context

The kodein-net website (marketing/portfolio site for Kodein Koders) was last updated in December 2021 and uses a Kotlin/JS + React stack built with Gradle. The project **cannot build anymore**: its package repositories (jcenter, Bintray) are dead, and the Kotlin/JS wrapper libraries have been completely reorganized with breaking API changes. An in-place upgrade would require rewriting all 88 Kotlin files anyway, so we're migrating to Astro — a modern static site generator that's ideal for marketing sites.

The backend (a Google Cloud Function for the contact form) will be replaced by an Astro API route or platform serverless function.

---

## Phase 1: Project Setup & Design System

**Initialize Astro project alongside existing code:**
- `npm create astro@latest` in a new directory (or in-place, moving old code to `legacy/`)
- TypeScript, no UI framework initially (pure Astro components for static pages)
- Add Preact integration for the contact form island (`@astrojs/preact`)

**Port the design system to CSS custom properties:**
- Source: `front/src/main/kotlin/net.kodein/charter/KodeinColors.kt`
- Create `src/styles/colors.css` with `--color-orange: #E8441F`, `--color-purple: #921F81`, etc.
- Source: `front/src/main/kotlin/net.kodein/charter/KodeinStyles.kt`
- Port typography scales, button styles, responsive breakpoints to CSS
- Copy static assets: fonts (`front/src/main/resources/picon/`), images (`front/src/main/resources/imgs/`, `logos/`), favicon

**Set up i18n routing:**
- Astro's built-in i18n: English at `/`, French at `/fr/`
- Create content structure for bilingual strings (TypeScript objects or JSON, mirroring the 12 EN + 12 FR string files in `front/src/main/kotlin/net.kodein/lang/`)

## Phase 2: Layout & Shared Components

**Port as Astro components (zero JS):**
- `MenuTop` → `src/components/MenuTop.astro` (mobile toggle via minimal `<script>`)
  - Source: `front/src/main/kotlin/net.kodein/components/MenuTop.kt`
- `Footer` → `src/components/Footer.astro`
  - Source: `front/src/main/kotlin/net.kodein/components/Footer.kt`
- `Cover` → `src/components/Cover.astro`
  - Source: `front/src/main/kotlin/net.kodein/components/Cover.kt`
- `ContentRow`, `LayerSeparator`, `Separator`, `KodeinLogo` → Astro components
  - Sources in `front/src/main/kotlin/net.kodein/components/`
- `BaseLayout.astro` — HTML shell with meta tags, Plausible analytics, font loading
  - Source for meta/HTML structure: `front/html/src/main/kotlin/main.kt`

## Phase 3: Pages

Port all 8 pages as Astro pages using the layout + components from Phase 2:

| Page | Source files | Target |
|------|-------------|--------|
| Home | `pages/home/Home.kt` + fragments | `src/pages/index.astro` + `src/pages/fr/index.astro` |
| Services | `pages/services/Services.kt` + fragments | `src/pages/services.astro` + `fr/` |
| Training | `pages/training/Training.kt` + fragments | `src/pages/training.astro` + `fr/` |
| OSS | `pages/oss/Oss.kt` + fragments | `src/pages/oss.astro` + `fr/` |
| Team | `pages/team/Team.kt` + fragments | `src/pages/team.astro` + `fr/` |
| Blog | `pages/blog/Blog.kt` + fragments | `src/pages/blog.astro` + `fr/` |
| Contact | `pages/contact/Contact.kt` + fragments | `src/pages/contact.astro` + `fr/` |
| Credits | `pages/credits/Credits.kt` + fragments | `src/pages/credits.astro` + `fr/` |

**Content extraction:** Read each page's Kotlin string file (e.g., `lang/en/EnglishHomeStrings.kt`, `lang/fr/FrenchHomeStrings.kt`) and port the text content. Note that some strings contain HTML markup via Kotlin DSL (`br {}`, `span("nowrap") {}`, `b {}`) — these become plain HTML.

**Image handling:** Replace the custom `picture` + `illustrationWidths` logic with Astro's built-in `<Image>` / `<Picture>` components for automatic optimization.

## Phase 4: Contact Form

- Create a Preact island component for the contact form (the only interactive element)
- Replace the Google Cloud Function with an Astro API route (`src/pages/api/contact.ts`)
  - Validate email
  - Send via SendGrid (same logic as `back/src/main/kotlin/net/kodein/Main.kt`, ~77 lines)
  - CORS not needed since it's same-origin now
- Requires a deployment platform that supports server routes (Vercel, Netlify, or Cloudflare Pages)

## Phase 5: Deployment & Cleanup

- Choose deployment platform: **Cloudflare Pages** or **Netlify** (free tier, edge CDN, serverless functions for the contact form)
- Set up `SENDGRID_API_KEY` as environment variable on the platform
- Configure custom domain (`kodein.net`) via CNAME
- Add Plausible analytics script to the base layout
- Remove old Kotlin/Gradle code once the new site is verified
- Optionally set up GitHub Actions for preview deploys on PRs

---

## Key Source Files Reference

| Purpose | Current file |
|---------|-------------|
| Colors/design tokens | `front/src/main/kotlin/net.kodein/charter/KodeinColors.kt` |
| Typography/styles | `front/src/main/kotlin/net.kodein/charter/KodeinStyles.kt` |
| Page definitions | `front/src/main/kotlin/net.kodein/pages.kt` |
| Language config | `front/src/main/kotlin/net.kodein/languages.kt` |
| EN strings (×12) | `front/src/main/kotlin/net.kodein/lang/en/English*.kt` |
| FR strings (×12) | `front/src/main/kotlin/net.kodein/lang/fr/French*.kt` |
| HTML template/meta | `front/html/src/main/kotlin/main.kt` |
| Contact backend | `back/src/main/kotlin/net/kodein/Main.kt` |
| Static assets | `front/src/main/resources/` (imgs, logos, picon fonts, favicon) |

## Verification

1. `npm run dev` — all 8 pages render correctly in both EN and FR
2. Visual comparison with the current live site (if still up) or screenshots
3. Contact form submits successfully and emails arrive via SendGrid
4. Lighthouse audit: performance score should be higher than original (zero JS on static pages)
5. All responsive breakpoints work (mobile, tablet, desktop)
6. `npm run build` produces a clean static build with no errors
