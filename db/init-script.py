import random

def generate_points(num_points, grid_size):
    points = set()
    while len(points) < num_points:
        x = random.randint(0, grid_size)
        y = random.randint(0, grid_size)
        points.add((x, y))
    return points

def generate_line_points(num_lines, points_per_line, grid_size):
    line_points = set()
    for _ in range(num_lines):
        # Choose a random line type: horizontal, vertical, or diagonal
        line_type = random.choice(['horizontal', 'vertical', 'diagonal'])
        if line_type == 'horizontal':
            y = random.randint(0, grid_size)
            xs = random.sample(range(grid_size + 1), points_per_line)
            for x in xs:
                line_points.add((x, y))
        elif line_type == 'vertical':
            x = random.randint(0, grid_size)
            ys = random.sample(range(grid_size + 1), points_per_line)
            for y in ys:
                line_points.add((x, y))
        else: # diagonal
            start = random.randint(0, grid_size - points_per_line)
            for i in range(points_per_line):
                line_points.add((start + i, start + i))
    return line_points

# Configuration
num_random_points = 10000   # Total number of random points
num_lines = 50              # Number of lines to generate
points_per_line = 100       # Points per line
grid_size = 1000            # Size of the grid (both x and y range from 0 to grid_size)

# Generate points
random_points = generate_points(num_random_points, grid_size)
line_points = generate_line_points(num_lines, points_per_line, grid_size)
all_points = random_points.union(line_points)

# Create a single SQL statement
values = ', '.join([f"({x}, {y})" for x, y in all_points])
sql_statement = f"INSERT INTO points (x, y) VALUES {values};"

# Output to a file
with open('init.sql', 'w') as file:
    file.write(sql_statement + '\n')
