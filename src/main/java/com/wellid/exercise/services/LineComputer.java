package com.wellid.exercise.services;

import com.wellid.exercise.models.Fraction;
import com.wellid.exercise.models.Line;
import com.wellid.exercise.entities.PointEntity;
import com.wellid.exercise.repositories.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LineComputer
{
    private final PointRepository pointRepository;

    private static final int BATCH_SIZE = 1000;

    public void compute(int n, BufferedWriter writer) throws IOException
    {
        Pageable pageable = PageRequest.of(0, BATCH_SIZE);
        boolean isFirstLine = true;
        Page<PointEntity> page;

        do
        {
            page = this.pointRepository.findAll(pageable);
            List<PointEntity> points = page.getContent();
            Map<Integer, Set<PointEntity>> lines = new HashMap<>();

            // Process each batch of points
            for (int i = 0; i < points.size(); i++)
            {
                for (int j = i + 1; j < points.size(); j++)
                {
                    var line = calculateLine(points.get(i), points.get(j));
                    var lineKey = line.hashCode();

                    var mapEntry = lines.get(lineKey);

                    if (mapEntry == null) {
                        var listOfPoints = new LinkedHashSet<PointEntity>();
                        listOfPoints.add(points.get(i));
                        listOfPoints.add(points.get(j));

                        lines.put(lineKey, listOfPoints);
                    }
                    else {
                        mapEntry.add(points.get(i));
                        mapEntry.add(points.get(j));
                    }
                }
            }

            // Write lines from this batch that have at least N points
            for (var entry : lines.entrySet()) {
                if (entry.getValue().size() >= n) {
                    if (!isFirstLine) {
                        writer.write(",");
                    }

                    writer.write("\n" + entry.getValue());
                    isFirstLine = false;
                }
            }

            pageable = page.nextPageable();
        } while (page.hasNext());
    }

    private Line calculateLine(PointEntity p1, PointEntity p2)
    {
        var deltaY = p2.getY() - p1.getY();
        var deltaX = p2.getX() - p1.getX();

        if (deltaX == 0) {
            return new Line(null, p1.getX());
        }

        var gcd = gcd(deltaY, deltaX);

        var slope = new Fraction(deltaY / gcd, deltaX / gcd);
        var intercept = p1.getY() - slope.toDouble() * p1.getX();

        return new Line(slope, intercept);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
